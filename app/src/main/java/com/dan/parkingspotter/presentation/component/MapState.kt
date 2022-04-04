package com.dan.parkingspotter.presentation.component

import com.dan.parkingspotter.domain.model.ParkingSpot
import com.google.maps.android.compose.MapProperties

data class MapState(
    val parkingSpots: List<ParkingSpot> = emptyList(),
    val properties: MapProperties = MapProperties(),
    val isNightStyleActive: Boolean = false,
)
