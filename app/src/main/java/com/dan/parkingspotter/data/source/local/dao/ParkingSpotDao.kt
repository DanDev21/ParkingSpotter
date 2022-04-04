package com.dan.parkingspotter.data.source.local.dao

import androidx.room.*
import com.dan.parkingspotter.data.entity.ParkingSpot
import kotlinx.coroutines.flow.Flow

@Dao
interface ParkingSpotDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertParkingSpot(spot: ParkingSpot)

    @Delete
    suspend fun deleteParkingSpot(spot: ParkingSpot)

    @Query("select * from parking_spot")
    fun getAllParkingSpots(): Flow<List<ParkingSpot>>
}