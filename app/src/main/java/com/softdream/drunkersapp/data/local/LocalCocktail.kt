package com.softdream.drunkersapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.softdream.drunkersapp.domain.Cocktail

@Entity(tableName = "cocktails")
data class LocalCocktail(

    @ColumnInfo(name = "idDrink")
    @PrimaryKey
    val id: String,

    @ColumnInfo(name = "strDrink")
    val name: String?,

    @ColumnInfo(name = "strCategory")
    val category: String?,

    @ColumnInfo(name = "strAlcoholic")
    val alcoholic: String?,

    @ColumnInfo(name = "strGlass")
    val glass: String?,

    @ColumnInfo(name = "strInstructions")
    val instructions: String?,

    @ColumnInfo(name = "strDrinkThumb")
    val imageURL: String?,

    @ColumnInfo(name = "strIngredient1")
    val ingredient1: String?,

    @ColumnInfo(name = "strIngredient2")
    val ingredient2: String?,

    @ColumnInfo(name = "strIngredient3")
    val ingredient3: String?,

    @ColumnInfo(name = "strIngredient4")
    val ingredient4: String?,

    @ColumnInfo(name = "strIngredient5")
    val ingredient5: String?,

    @ColumnInfo(name = "strIngredient6")
    val ingredient6: String?,

    @ColumnInfo(name = "strIngredient7")
    val ingredient7: String?,

    @ColumnInfo(name = "strIngredient8")
    val ingredient8: String?,

    @ColumnInfo(name = "strIngredient9")
    val ingredient9: String?,

    @ColumnInfo(name = "strIngredient10")
    val ingredient10: String?,

    @ColumnInfo(name = "strIngredient11")
    val ingredient11: String?,

    @ColumnInfo(name = "strIngredient12")
    val ingredient12: String?,

    @ColumnInfo(name = "strIngredient13")
    val ingredient13: String?,

    @ColumnInfo(name = "strIngredient14")
    val ingredient14: String?,

    @ColumnInfo(name = "strIngredient15")
    val ingredient15: String?,

    @ColumnInfo(name = "strMeasure1")
    val measure1: String?,

    @ColumnInfo(name = "strMeasure2")
    val measure2: String?,

    @ColumnInfo(name = "strMeasure3")
    val measure3: String?,

    @ColumnInfo(name = "strMeasure4")
    val measure4: String?,

    @ColumnInfo(name = "strMeasure5")
    val measure5: String?,

    @ColumnInfo(name = "strMeasure6")
    val measure6: String?,

    @ColumnInfo(name = "strMeasure7")
    val measure7: String?,

    @ColumnInfo(name = "strMeasure8")
    val measure8: String?,

    @ColumnInfo(name = "strMeasure9")
    val measure9: String?,

    @ColumnInfo(name = "strMeasure10")
    val measure10: String?,

    @ColumnInfo(name = "strMeasure11")
    val measure11: String?,

    @ColumnInfo(name = "strMeasure12")
    val measure12: String?,

    @ColumnInfo(name = "strMeasure13")
    val measure13: String?,

    @ColumnInfo(name = "strMeasure14")
    val measure14: String?,

    @ColumnInfo(name = "strMeasure15")
    val measure15: String?
)

fun LocalCocktail.toCocktail() =
    Cocktail(
        id = id,
        name = name.orEmpty(),
        category = category.orEmpty(),
        alcoholic = alcoholic.orEmpty(),
        glass = glass.orEmpty(),
        instructions = instructions.orEmpty(),
        imageURL = imageURL.orEmpty(),
        ingredientsMap = mapOf(
            ingredient1 to measure1,
            ingredient2 to measure2,
            ingredient3 to measure3,
            ingredient4 to measure4,
            ingredient5 to measure5,
            ingredient6 to measure6,
            ingredient7 to measure7,
            ingredient8 to measure8,
            ingredient9 to measure9,
            ingredient10 to measure10,
            ingredient11 to measure11,
            ingredient12 to measure12,
            ingredient13 to measure13,
            ingredient14 to measure14,
            ingredient15 to measure15
        )
    )

