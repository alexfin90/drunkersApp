package com.softdream.drunkersapp.presentation.list

import com.softdream.drunkersapp.domain.Cocktail

data class CocktailScreenState(
    val cocktails: List<Cocktail> = emptyList(),
    val isLoading: Boolean = true,
    var error: String = "",
    var toastMessage: String = ""
)
