package com.softdream.drunkersapp.domain

import com.softdream.drunkersapp.data.CocktailRepository
import javax.inject.Inject

class GetCocktailByNameUseCase @Inject constructor(private val repository: CocktailRepository) {
    suspend operator fun invoke(name: String): Cocktail? {
        return repository.getCocktailByName(name)
    }
}