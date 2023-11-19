package com.softdream.drunkersapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CocktailDao {

    @Query("SELECT * FROM cocktails")
    suspend fun getAll(): List<LocalCocktail>

    @Query("SELECT * FROM cocktails WHERE strDrink=:name")
    suspend fun getCocktailByName(name: String) : LocalCocktail?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(cocktails: List<LocalCocktail>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCocktail(cocktails: LocalCocktail)
}