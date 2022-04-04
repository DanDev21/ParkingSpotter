package com.dan.parkingspotter.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dan.parkingspotter.domain.model.ParkingSpot
import com.dan.parkingspotter.domain.repository.IParkingSpotRepository
import com.dan.parkingspotter.presentation.component.MapState
import com.dan.parkingspotter.presentation.theme.MapStyles
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val repository: IParkingSpotRepository
) : ViewModel() {

    var mapState by mutableStateOf(MapState())

    init {
        viewModelScope.launch {
            repository.getAll().collectLatest {
                mapState = mapState.copy(parkingSpots = it)
            }
        }
    }

    fun handle(event: UIEvent) = when (event) {
        is ToggleMapStyle -> toggleMapStyle()
        is OnMapLongClickEvent -> insertParkingSpot(event.latLng)
        is OnInfoWindowLongClickEvent -> deleteParkingSpot(event.spot)
    }

    private fun toggleMapStyle() {
        mapState = mapState.copy(
            properties = mapState.properties.copy(
                mapStyleOptions = if (mapState.isNightStyleActive) null else MapStyles.night
            ),
            isNightStyleActive = !mapState.isNightStyleActive
        )
    }

    private fun insertParkingSpot(latLng: LatLng) {
        viewModelScope.launch {
            repository.insert(ParkingSpot(
                lat = latLng.latitude,
                lng = latLng.longitude,
            ))
        }
    }

    private fun deleteParkingSpot(spot: ParkingSpot) {
        viewModelScope.launch { repository.delete(spot) }
    }
}