package com.softdream.drunkersapp.presentation.list

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softdream.drunkersapp.R
import com.softdream.drunkersapp.data.di.MainDispatcher
import com.softdream.drunkersapp.domain.GetCocktailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailViewModel @Inject constructor(
    private val getCocktailsUseCase: GetCocktailsUseCase,
    @MainDispatcher private val dispatcher: CoroutineDispatcher,
    @ApplicationContext private val application: Context?
) : ViewModel() {
    //ViewModel only modify the UI state  and call domain layer
    private val _state = mutableStateOf(CocktailScreenState(listOf()))

    //expose the state to compose without possibility to modify state
    val state: State<CocktailScreenState> get() = _state

    var textFieldValue = mutableStateOf(TextFieldValue(""))

    private val errorHandle =
        CoroutineExceptionHandler { _, exception ->
            run {
                Log.d(
                    this.javaClass.simpleName,
                    exception.message ?: application.let { it!!.getString(R.string.generic_error) }
                )
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = exception.message
                        ?: application.let { it!!.getString(R.string.generic_error) }
                )
                exception.printStackTrace()
            }
        }

    init {
        //init list with data
        getCocktails(textFieldValue.value.text)
    }

    private fun getCocktails(text: String) {
        //Note launch use for default  Dispatchers.MAIN
        viewModelScope.launch(errorHandle + dispatcher) {
            val useCase = getCocktailsUseCase(text)
            _state.value = _state.value.copy(cocktails = useCase.cocktails, isLoading = false, toastMessage = useCase.infomessage)
        }
    }

    fun onTextFieldValueChanged(newValue: TextFieldValue) {
        textFieldValue.value = newValue
        getCocktails(textFieldValue.value.text)
    }

    fun retryGetCocktails(text: String) {
        _state.value = _state.value.copy(isLoading = true, error = "", toastMessage = "")
        getCocktails(text)
    }

}