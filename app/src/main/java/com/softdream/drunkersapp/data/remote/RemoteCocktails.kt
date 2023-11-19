package com.softdream.drunkersapp.data.remote

import com.google.gson.annotations.SerializedName

data class RemoteCocktails(

    @SerializedName("drinks")
    val cocktails: List<RemoteCocktail>

)