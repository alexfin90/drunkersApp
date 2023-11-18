package com.softdream.drunkersapp.domain

import com.softdream.drunkersapp.data.LocationRepository
import javax.inject.Inject

class GetLocationByIDUseCase @Inject constructor(private val repository: LocationRepository) {
    suspend operator fun invoke(id: String): Location? {
        return repository.getLocationByID(id)
    }
}