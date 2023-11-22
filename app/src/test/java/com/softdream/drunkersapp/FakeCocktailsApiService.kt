package com.softdream.drunkersapp

import com.softdream.drunkersapp.data.MockCocktail
import com.softdream.drunkersapp.data.remote.CocktailApiService
import com.softdream.drunkersapp.data.remote.RemoteCocktails
import kotlinx.coroutines.delay

class FakeCocktailsApiService : CocktailApiService {
    override suspend fun getCocktails(s: String): RemoteCocktails {
        //simulate api call
        delay(500)
        return MockCocktail.getRemoteCocktails()
    }

    override suspend fun getCocktailsByName(s: String): RemoteCocktails {
        delay(500)
        return MockCocktail.getRemoteCocktails()
    }
}