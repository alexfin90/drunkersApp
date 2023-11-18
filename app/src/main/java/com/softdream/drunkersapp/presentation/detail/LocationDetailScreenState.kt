package com.softdream.drunkersapp.presentation.detail

import com.softdream.drunkersapp.domain.Location

data class LocationDetailScreenState(
    val location: Location? = null,
    val isLoading: Boolean = true,
    val error: String = "",
    val lastIDLocation: String = ""
)
