package com.dan.parkingspotter.domain.repository

import com.dan.parkingspotter.domain.model.ParkingSpot
import kotlinx.coroutines.flow.Flow

interface IParkingSpotRepository {

    suspend fun insert(spot: ParkingSpot)

    suspend fun delete(spot: ParkingSpot)

    fun getAll(): Flow<List<ParkingSpot>>
}