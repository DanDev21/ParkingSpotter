package com.dan.parkingspotter.presentation

import com.dan.parkingspotter.domain.model.ParkingSpot
import com.google.android.gms.maps.model.LatLng

sealed class UIEvent

object ToggleMapStyle : UIEvent()
data class OnMapLongClickEvent(val latLng: LatLng) : UIEvent()
data class OnInfoWindowLongClickEvent(val spot: ParkingSpot) : UIEvent()