package com.dan.parkingspotter.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dan.parkingspotter.data.entity.ParkingSpot
import com.dan.parkingspotter.data.source.local.dao.ParkingSpotDao
import com.dan.parkingspotter.util.LocalDatabaseProperties

@Database(
    version = LocalDatabaseProperties.VERSION,
    entities = [
        ParkingSpot::class
    ],
)
abstract class LocalDatabase : RoomDatabase() {

    abstract val dao: ParkingSpotDao
}