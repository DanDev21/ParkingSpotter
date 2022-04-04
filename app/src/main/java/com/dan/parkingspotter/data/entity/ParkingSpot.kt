package com.dan.parkingspotter.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "parking_spot"
)
data class ParkingSpot(
    val lat: Double,
    val lng: Double,
    @PrimaryKey val id: Int? = null
)
