package com.softdream.drunkersapp

import com.softdream.drunkersapp.data.CocktailRepository
import com.softdream.drunkersapp.data.MockCocktail
import com.softdream.drunkersapp.domain.GetCocktailsUseCase
import com.softdream.drunkersapp.presentation.list.CocktailScreenState
import com.softdream.drunkersapp.presentation.list.CocktailViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class CocktailViewModelTest {

    // Using the test dispatcher and not the main like in prod env
    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    @Test
    fun initialStateTest() = scope.runTest {
        val viewModel = getViewModel()
        val initialState = viewModel.state.value
        assert(
            initialState == CocktailScreenState(
                cocktails = emptyList(),
                isLoading = true,
                error = "",
                toastMessage = ""
            )
        )
    }

    @Test
    fun stateWithContextTest() = scope.runTest {
        val testVM = getViewModel()
        advanceUntilIdle()
        val currentSate = testVM.state.value
        println(currentSate.toString())
        println(CocktailScreenState(
            cocktails = MockCocktail.getDomainDrinks().cocktails,
            isLoading = false,
            error = "",
            toastMessage = ""
        ))
        assert(
            currentSate == CocktailScreenState(
                cocktails = MockCocktail.getDomainDrinks().cocktails,
                isLoading = false,
                error = "",
                toastMessage = ""
            )
        )
    }

    private fun getViewModel(): CocktailViewModel {
        val cocktailRepository = CocktailRepository(
            restInterface = FakeCocktailsApiService(),
            cocktailsDao = FakeCocktailsRoomDao(),
            dispatcher
        )
        val useCaseCocktail = GetCocktailsUseCase(cocktailRepository)
        return CocktailViewModel(
            getCocktailsUseCase = useCaseCocktail,
            dispatcher = dispatcher,
            null
        )
    }
}