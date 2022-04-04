package com.dan.parkingspotter.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dan.parkingspotter.presentation.component.MapScreen
import com.dan.parkingspotter.presentation.theme.ParkingSpotterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ParkingSpotterTheme {
                MapScreen()
            }
        }
    }
}