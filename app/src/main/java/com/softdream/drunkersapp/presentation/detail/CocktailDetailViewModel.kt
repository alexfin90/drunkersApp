package com.softdream.drunkersapp.presentation.detail

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softdream.drunkersapp.R
import com.softdream.drunkersapp.domain.GetCocktailByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CocktailDetailViewModel @Inject constructor(
    @ApplicationContext application: Context,
    private val getcocktailByNameUseCase: GetCocktailByNameUseCase,
    stateHandle: SavedStateHandle
) : ViewModel() {

    //ViewModel only modify the UI state  and call domain layer
    private var _state = mutableStateOf(CocktailDetailScreenState())

    //expose the state to compose without possibility to modify state
    val state: State<CocktailDetailScreenState> get() = _state

    private val errorHandle =
        CoroutineExceptionHandler { _, exception ->
            run {
                Log.d(
                    this.javaClass.simpleName,
                    exception.message ?: application.getString(R.string.generic_error)
                )
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = exception.message ?: application.getString(R.string.generic_error)
                )
                exception.printStackTrace()
            }
        }


    init {
        val lastNameCocktail = stateHandle.get<String>("cocktail_id") ?: ""
        _state.value = _state.value.copy(lastNameCocktail = lastNameCocktail)
        getCocktail(lastNameCocktail)
    }

    private fun getCocktail(name: String) {
        //Note launch use for default  Dispatchers.MAIN
        viewModelScope.launch(errorHandle) {
            val cocktail = getcocktailByNameUseCase(name)
            cocktail?.let {
                _state.value = _state.value.copy(cocktail = it, isLoading = false)
            }
        }
    }


    fun retryGetCocktail() {
        _state.value = _state.value.copy(isLoading = true, error = "")
        getCocktail(_state.value.lastNameCocktail)
    }
}