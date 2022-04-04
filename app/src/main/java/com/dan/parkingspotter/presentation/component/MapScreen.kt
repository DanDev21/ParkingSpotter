package com.dan.parkingspotter.presentation.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ToggleOff
import androidx.compose.material.icons.filled.ToggleOn
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dan.parkingspotter.R
import com.dan.parkingspotter.presentation.MapViewModel
import com.dan.parkingspotter.presentation.OnInfoWindowLongClickEvent
import com.dan.parkingspotter.presentation.OnMapLongClickEvent
import com.dan.parkingspotter.presentation.ToggleMapStyle
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker

@Composable
fun MapScreen(
    viewModel: MapViewModel = viewModel()
) {
    val scaffoldState = rememberScaffoldState()
    
    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.handle(ToggleMapStyle) }
            ) {
                Icon(
                    imageVector = getFloatingActionButtonImage(viewModel.mapState.isNightStyleActive),
                    contentDescription = stringResource(R.string.description_toggle_map_theme)
                )
            }
        }
    ) {
        val mapUiSettings = remember {
            MapUiSettings(zoomControlsEnabled = false)
        }

        GoogleMap(
            modifier = Modifier
                .fillMaxSize(),
            properties = viewModel.mapState.properties,
            uiSettings = mapUiSettings,
            onMapLongClick = {
                viewModel.handle(OnMapLongClickEvent(it))
            }
        ) {
            viewModel.mapState.parkingSpots.forEach { spot ->
                Marker(
                    position = LatLng(spot.lat, spot.lng),
                    title = "Parking Spot (${spot.lat}, ${spot.lng})",
                    snippet = stringResource(R.string.description_delete_parking_spot),
                    onInfoWindowLongClick = {
                        viewModel.handle(OnInfoWindowLongClickEvent(spot))
                    },
                    onClick = {
                        it.showInfoWindow(); true
                    },
                    icon = BitmapDescriptorFactory.defaultMarker(
                        BitmapDescriptorFactory.HUE_GREEN
                    )
                )
            }
        }
    }
}

private fun getFloatingActionButtonImage(on: Boolean) =
    if (on) Icons.Default.ToggleOff else Icons.Default.ToggleOn
