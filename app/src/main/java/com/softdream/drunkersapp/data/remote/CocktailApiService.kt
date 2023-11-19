package com.softdream.drunkersapp.data.remote


import retrofit2.http.GET
import retrofit2.http.Path

interface CocktailApiService {

        @GET("/api/json/v1/1/search.php?s=")
        suspend fun getCocktails():RemoteCocktails

        @GET("/api/json/v1/1/search.php?s={name}")
        suspend fun getCocktailsByName(@Path("name") name: String): RemoteCocktails
}