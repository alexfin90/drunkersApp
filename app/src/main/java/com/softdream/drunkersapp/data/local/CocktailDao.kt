package com.softdream.drunkersapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CocktailDao {

    @Query("SELECT * FROM cocktails")
    suspend fun getAll(): List<LocalCocktail>

    @Query(
        "SELECT * FROM cocktails WHERE strDrink LIKE '%' || :name || '%' " +
                "OR strIngredient1 LIKE '%' || :name || '%' " +
                "OR strIngredient2 LIKE '%' || :name || '%' " +
                "OR strIngredient3 LIKE '%' || :name || '%' " +
                "OR strIngredient4 LIKE '%' || :name || '%' " +
                "OR strIngredient5 LIKE '%' || :name || '%' " +
                "OR strIngredient6 LIKE '%' || :name || '%' " +
                "OR strIngredient7 LIKE '%' || :name || '%' " +
                "OR strIngredient8 LIKE '%' || :name || '%' " +
                "OR strIngredient9 LIKE '%' || :name || '%' " +
                "OR strIngredient10 LIKE '%' || :name || '%' " +
                "OR strIngredient11 LIKE '%' || :name || '%' " +
                "OR strIngredient12 LIKE '%' || :name || '%' " +
                "OR strIngredient13 LIKE '%' || :name || '%' " +
                "OR strIngredient14 LIKE '%' || :name || '%' " +
                "OR strIngredient15 LIKE '%' || :name || '%' "
    )
    suspend fun getAllByNameOrIngredients(name: String): List<LocalCocktail>

    @Query("SELECT * FROM cocktails WHERE strDrink=:name")
    suspend fun getCocktailByName(name: String): LocalCocktail?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(cocktails: List<LocalCocktail>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCocktail(cocktails: LocalCocktail)
}