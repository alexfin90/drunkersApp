package com.softdream.drunkersapp.presentation.detail

import com.softdream.drunkersapp.domain.Cocktail

data class CocktailDetailScreenState(
    val cocktail: Cocktail ? = null,
    val isLoading: Boolean = true,
    val error: String = "",
    val lastNameCocktail: String = ""
)