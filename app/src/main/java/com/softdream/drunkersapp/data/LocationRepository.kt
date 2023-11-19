package com.softdream.drunkersapp.data

import android.content.Context
import com.softdream.drunkersapp.R

import com.softdream.drunkersapp.data.di.IoDispatcher
import com.softdream.drunkersapp.data.local.LocationDao
import com.softdream.drunkersapp.data.local.toLocation
import com.softdream.drunkersapp.data.remote.LocationApiService
import com.softdream.drunkersapp.data.remote.toLocalLocation
import com.softdream.drunkersapp.domain.Location
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationRepository @Inject constructor(
    private var restInterface: LocationApiService,
    private var locationDao: LocationDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    @ApplicationContext private val application: Context? = null,
) {

    suspend fun getAllLocations(): List<Location> {
        return withContext(dispatcher) {
            try {
                refreshCache()
            } catch (e: Exception) {
                when (e) {
                    is UnknownHostException,
                    is ConnectException,
                    is HttpException -> {
                        if (locationDao.getAll().isEmpty())
                            throw Exception(
                                application?.getString(R.string.generic_error)
                            )
                    }
                    else -> throw e
                }
            }
            return@withContext locationDao.getAll().map { it.toLocation() }
        }
    }

    private suspend fun refreshCache() {

        //Note Retrofit  set behind the scenes Dispatchers.IO for all suspend methods from within its interface
        val locations = restInterface.getLocations()
        locationDao.addAll(locations.map { it.toLocalLocation() })
    }

    suspend fun getLocationByID(id: String): Location? {
        return withContext(dispatcher) {
            try {
                refreshCache(id)
            } catch (e: Exception) {
                when (e) {
                    is UnknownHostException,
                    is ConnectException,
                    is HttpException -> {
                        if (locationDao.getLocationByID(id) == null) {
                            throw Exception(

                            )
                        }
                    }
                    else -> throw e
                }
            }
            return@withContext locationDao.getLocationByID(id)?.toLocation()
        }
    }

    private suspend fun refreshCache(id: String) {
        val response = restInterface.getLocationByCode(id)
        locationDao.addLocation(response.first().toLocalLocation())
    }
}