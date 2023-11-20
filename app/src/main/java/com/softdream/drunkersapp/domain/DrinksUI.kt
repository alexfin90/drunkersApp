package com.softdream.drunkersapp.domain


data class Drinks(var cocktails: List<Cocktail>, val infomessage: String ="")

data class Cocktail(
    val id: String,
    val name: String = "",
    val category: String = "",
    val alcoholic: String = "",
    val glass: String = "",
    val instructions: String = "",
    val imageURL: String = "",
    val ingredientsMap: Map<String?, String?>?
)