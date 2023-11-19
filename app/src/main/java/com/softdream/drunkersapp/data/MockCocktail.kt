package com.softdream.drunkersapp.data

import com.softdream.drunkersapp.data.remote.RemoteCocktail
import com.softdream.drunkersapp.domain.Cocktail

object MockCocktail {

    fun getDomainCocktail() = arrayListOf(
        Cocktail(
            "1", "cocktail1", "cocktail",
            "shot", "shot", "lorem ipsum", "",
            mapOf("key1" to "value1", "key2" to "value2", "key3" to "value3")
        ),
        Cocktail(
            "2", "cocktail2", "cocktail",
            "shot", "shot", "lorem ipsum", "",
            mapOf("key1" to "value1", "key2" to "value2", "key3" to "value3")
        ),
        Cocktail(
            "3", "cocktail3", "cocktail",
            "shot", "shot", "lorem ipsum", "",
            mapOf("key1" to "value1", "key2" to "value2", "key3" to "value3")
        )
    )

    fun getRemoteCocktails() = MockCocktail.getDomainCocktail().map {
        RemoteCocktail(
            it.id,
            it.name,
            it.category,
            it.alcoholic,
            it.glass,
            it.instructions,
            it.imageURL,
            it.ingredientsMap?.keys?.elementAt(0),
            it.ingredientsMap?.keys?.elementAt(1),
            it.ingredientsMap?.keys?.elementAt(2),
            it.ingredientsMap?.keys?.elementAt(3),
            it.ingredientsMap?.keys?.elementAt(4),
            it.ingredientsMap?.keys?.elementAt(5),
            it.ingredientsMap?.keys?.elementAt(6),
            it.ingredientsMap?.keys?.elementAt(7),
            it.ingredientsMap?.keys?.elementAt(8),
            it.ingredientsMap?.keys?.elementAt(9),
            it.ingredientsMap?.keys?.elementAt(10),
            it.ingredientsMap?.keys?.elementAt(11),
            it.ingredientsMap?.keys?.elementAt(12),
            it.ingredientsMap?.keys?.elementAt(13),
            it.ingredientsMap?.keys?.elementAt(14),
            it.ingredientsMap?.values?.elementAt(0),
            it.ingredientsMap?.values?.elementAt(1),
            it.ingredientsMap?.values?.elementAt(2),
            it.ingredientsMap?.values?.elementAt(3),
            it.ingredientsMap?.values?.elementAt(4),
            it.ingredientsMap?.values?.elementAt(5),
            it.ingredientsMap?.values?.elementAt(6),
            it.ingredientsMap?.values?.elementAt(7),
            it.ingredientsMap?.values?.elementAt(8),
            it.ingredientsMap?.values?.elementAt(9),
            it.ingredientsMap?.values?.elementAt(10),
            it.ingredientsMap?.values?.elementAt(11),
            it.ingredientsMap?.values?.elementAt(12),
            it.ingredientsMap?.values?.elementAt(13),
            it.ingredientsMap?.values?.elementAt(14)
        )
    }
}