package com.softdream.drunkersapp.domain

import com.softdream.drunkersapp.data.CocktailRepository
import javax.inject.Inject

class GetCocktailsUseCase @Inject constructor(private val repository: CocktailRepository) {
    //contain only one method invoke for business logic
    //ordering for population
    suspend operator fun invoke(): Drinks {
        val drinks = repository.getAllCocktails()
        drinks.cocktails.sortedBy { it.name }
        return drinks
    }
}