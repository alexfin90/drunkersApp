package com.softdream.drunkersapp

import com.softdream.drunkersapp.data.CocktailRepository
import com.softdream.drunkersapp.domain.GetCocktailByNameUseCase
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
    fun cocktailTest () = scope.runTest {

        val cocktailsRepository = CocktailRepository(FakeCocktailsApiService(),FakeCocktailsRoomDao(),dispatcher)
        val getCocktailsUseCase = GetCocktailByNameUseCase(cocktailsRepository)

        val cocktailNameToSearch = "cocktail1"

        //Preload Data
        cocktailsRepository.getCocktailByName(cocktailNameToSearch)
        advanceUntilIdle()


        //Execute UseCase
        val cocktailTest = getCocktailsUseCase.invoke(cocktailNameToSearch)
        advanceUntilIdle()


        println(cocktailTest.toString())

        //assert if items name are note the same
       assert(cocktailTest?.name == cocktailNameToSearch)

    }

}