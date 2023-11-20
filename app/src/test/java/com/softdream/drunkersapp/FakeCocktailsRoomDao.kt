package com.softdream.drunkersapp

import com.softdream.drunkersapp.data.local.CocktailDao
import com.softdream.drunkersapp.data.local.LocalCocktail
import kotlinx.coroutines.delay

class FakeCocktailsRoomDao : CocktailDao {

    private var cocktails =  ArrayList<LocalCocktail>()

    override suspend fun getAll(): List<LocalCocktail> {
        delay(500)
        return this.cocktails
    }

    override suspend fun getAllByNameOrIngredients(name: String): List<LocalCocktail> {
        return this.cocktails
    }

    override suspend fun getCocktailByName(name: String): LocalCocktail? {
        return this.cocktails.first {name == it.name}
    }

    override suspend fun addAll(cocktails: List<LocalCocktail>) {
       this.cocktails = cocktails as ArrayList<LocalCocktail>
    }

    override suspend fun addCocktail(cocktails: LocalCocktail) {
        this.cocktails.add(cocktails)
    }
}