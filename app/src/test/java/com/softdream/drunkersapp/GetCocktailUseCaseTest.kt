package com.softdream.drunkersapp

import com.softdream.drunkersapp.data.CocktailRepository
import com.softdream.drunkersapp.data.MockCocktail
import com.softdream.drunkersapp.domain.GetCocktailsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class GetCocktailUseCaseTest {

    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    @Test
    fun sortCocktailTest () = scope.runTest {

        val cocktailsRepository = CocktailRepository(FakeCocktailsApiService(),FakeCocktailsRoomDao(),dispatcher)
        val getSortedCocktailsUseCase = GetCocktailsUseCase(cocktailsRepository)

        //Preload Data
        cocktailsRepository.getAllCocktails("")
        advanceUntilIdle()


        //Execute UseCase
        val sortedCocktails = getSortedCocktailsUseCase.invoke("")
        advanceUntilIdle()

        val mockedCocktails = MockCocktail.getDomainDrinks()
        println(sortedCocktails.toString())
        println(mockedCocktails.toString())

        //assert if first items name are note the same
        assert(sortedCocktails.cocktails[0].name == mockedCocktails.cocktails[0].name)

    }

}