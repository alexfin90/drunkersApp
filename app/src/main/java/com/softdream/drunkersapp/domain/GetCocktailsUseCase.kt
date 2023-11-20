package com.softdream.drunkersapp.domain

import com.softdream.drunkersapp.data.CocktailRepository
import javax.inject.Inject

class GetCocktailsUseCase @Inject constructor(private val repository: CocktailRepository) {
    //contain only one method invoke for business logic
    //order by Descending alphabetic name
    suspend operator fun invoke(text: String): Drinks {
        val drinks = repository.getAllCocktails(text)
        drinks.cocktails = drinks.cocktails.sortedByDescending { it.name }
        return drinks
    }
}