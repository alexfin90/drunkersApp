package com.softdream.drunkersapp.data

import com.softdream.drunkersapp.data.remote.RemoteCocktail
import com.softdream.drunkersapp.data.remote.RemoteCocktails
import com.softdream.drunkersapp.domain.Cocktail
import com.softdream.drunkersapp.domain.Drinks

object MockCocktail {

    fun getDomainDrinks(): Drinks {
        val cocktails = arrayListOf(
            Cocktail(
                "3", "cocktail3", "cocktail",
                "shot", "shot", "lorem ipsum", "", mapOf(null to "")
            ),
            Cocktail(
                "2", "cocktail2", "cocktail",
                "shot", "shot", "lorem ipsum", "",mapOf(null to "")
            ),
            Cocktail(
                "1", "cocktail1", "cocktail",
                "shot", "shot", "lorem ipsum", "",mapOf(null to "")
            )
        )

        return Drinks(cocktails, "")
    }

    fun getRemoteCocktails(): RemoteCocktails {

        val drinks = getDomainDrinks()
        val cocktails = drinks.cocktails.map {
            RemoteCocktail(
                id = it.id,
                name = it.name,
                category = it.category,
                alcoholic = it.alcoholic,
                glass = it.glass,
                instructions = it.instructions,
                imageURL = it.imageURL
            )
        }

        return RemoteCocktails(cocktails = cocktails)

    }
}
