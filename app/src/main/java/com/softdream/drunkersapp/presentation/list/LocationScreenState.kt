package com.softdream.drunkersapp.presentation.list


import com.softdream.drunkersapp.domain.Location

data class LocationScreenState(
    val locations: List<Location> = emptyList(),
    val isLoading: Boolean = true,
    var error: String = ""
)