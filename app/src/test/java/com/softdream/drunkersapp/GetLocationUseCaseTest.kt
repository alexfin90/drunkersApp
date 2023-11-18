package com.softdream.drunkersapp

import FakeApiService
import com.softdream.drunkersapp.data.LocationRepository
import com.softdream.drunkersapp.data.MockContent
import com.softdream.drunkersapp.domain.GetLocationsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class GetLocationUseCaseTest {

    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    @Test
    fun sortLocationTest () = scope.runTest {

        val locationRepository = LocationRepository(FakeApiService(),FakeRoomDao(),dispatcher)
        val getSortedLocationUseCase = GetLocationsUseCase(locationRepository)

        //Preload Data
        locationRepository.getAllLocations()
        advanceUntilIdle()


        //Execute UseCase
        val sortedLocations = getSortedLocationUseCase.invoke()
        advanceUntilIdle()

        val mockedLocation = MockContent.getDomainLocation()
        println(sortedLocations.toString())
        println(mockedLocation.toString())

        assert(sortedLocations == mockedLocation)

    }

}