package com.dan.parkingspotter.data.mapper

import com.dan.parkingspotter.data.entity.ParkingSpot

fun ParkingSpot.toParkingSpot() =
    com.dan.parkingspotter.domain.model.ParkingSpot(
        lat = lat,
        lng = lng,
        id = id
    )

fun com.dan.parkingspotter.domain.model.ParkingSpot.toParkingSpot() =
    ParkingSpot(
        lat = lat,
        lng = lng,
        id = id
    )