package com.softdream.drunkersapp.data

import android.content.Context
import com.softdream.drunkersapp.R
import com.softdream.drunkersapp.data.di.IoDispatcher
import com.softdream.drunkersapp.data.local.CocktailDao
import com.softdream.drunkersapp.data.local.toCocktail
import com.softdream.drunkersapp.data.remote.CocktailApiService
import com.softdream.drunkersapp.data.remote.toLocalCocktail
import com.softdream.drunkersapp.domain.Cocktail
import com.softdream.drunkersapp.domain.Drinks
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CocktailRepository @Inject constructor(
    private var restInterface: CocktailApiService,
    private var cocktailsDao: CocktailDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    @ApplicationContext private val application: Context? = null,
) {

    suspend fun getAllCocktails(name: String): Drinks {
        var infoMessage = ""
        return withContext(dispatcher) {
            try {
                refreshAllCache(name)
            } catch (e: Exception) {
                when (e) {
                    is UnknownHostException,
                    is ConnectException,
                    is HttpException -> {
                        if (cocktailsDao.getAll().isEmpty()) {
                            throw Exception(application?.getString(R.string.generic_error))
                        } else {
                            infoMessage = application?.getString(R.string.ko_internet).toString()
                        }
                    }
                    else -> throw e
                }
            }
            return@withContext Drinks(cocktails = cocktailsDao.getAllByName(name).map { it.toCocktail() }, infomessage = infoMessage)
        }
    }


    private suspend fun refreshAllCache(name: String) {
        //Note Retrofit set behind the scenes Dispatchers.IO for all suspend methods from within its interface
        val drinks = restInterface.getCocktails(name)
        drinks.cocktails?.let { cocktails ->
            cocktailsDao.addAll(cocktails.map { it.toLocalCocktail() })
        }
    }

    suspend fun getCocktailByName(name: String): Cocktail? {
        return withContext(dispatcher) {
            try {
                refreshSingleCache(name)
            } catch (e: Exception) {
                when (e) {
                    is UnknownHostException,
                    is ConnectException,
                    is HttpException -> {
                        if (cocktailsDao.getCocktailByName(name) == null) {
                            throw Exception()
                        }
                    }
                    else -> throw e
                }
            }
            return@withContext cocktailsDao.getCocktailByName(name)?.toCocktail()
        }
    }

    private suspend fun refreshSingleCache(name: String) {
        val response = restInterface.getCocktailsByName(name)
        response.cocktails?.let { cocktails ->
            cocktailsDao.addCocktail(cocktails.first().toLocalCocktail())
        }
    }

}