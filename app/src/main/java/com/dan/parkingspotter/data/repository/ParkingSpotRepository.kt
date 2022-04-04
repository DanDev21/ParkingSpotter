package com.dan.parkingspotter.data.repository

import com.dan.parkingspotter.data.mapper.toParkingSpot
import com.dan.parkingspotter.data.source.local.dao.ParkingSpotDao
import com.dan.parkingspotter.domain.model.ParkingSpot
import com.dan.parkingspotter.domain.repository.IParkingSpotRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ParkingSpotRepository(
    private val dao: ParkingSpotDao
) : IParkingSpotRepository {

    override suspend fun insert(spot: ParkingSpot) {
        dao.insertParkingSpot(spot.toParkingSpot())
    }

    override suspend fun delete(spot: ParkingSpot) {
        dao.deleteParkingSpot(spot.toParkingSpot())
    }

    override fun getAll(): Flow<List<ParkingSpot>> {
        return dao.getAllParkingSpots().map { spots ->
            spots.map { it.toParkingSpot() }
        }
    }
}