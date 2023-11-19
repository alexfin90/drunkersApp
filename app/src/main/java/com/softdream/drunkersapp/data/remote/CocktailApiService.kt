package com.softdream.drunkersapp.data.remote


import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailApiService {

    @GET("/api/json/v1/1/search.php?s=")
    suspend fun getCocktails(): RemoteCocktails

    @GET("/api/json/v1/1/search.php")
    suspend fun getCocktailsByName(@Query("s") s: String): RemoteCocktails
}