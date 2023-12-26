package com.example.haminjast.ui.screen.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.haminjast.R
import com.utsman.osmandcompose.Marker
import com.utsman.osmandcompose.OpenStreetMap
import com.utsman.osmandcompose.rememberCameraState
import com.utsman.osmandcompose.rememberMarkerState
import org.osmdroid.util.GeoPoint

@Composable
fun MapScreen(){

    val cameraState = rememberCameraState {
        geoPoint = GeoPoint(35.7219, 51.3347)
        zoom = 12.0
    }
    val depokMarkerState = rememberMarkerState(
        geoPoint = GeoPoint(35.7219, 51.3347)
    )

    Box(modifier = Modifier.fillMaxSize()) {
        OpenStreetMap(
            modifier = Modifier.fillMaxSize(),
            cameraState = cameraState,
            onMapLongClick = {
                depokMarkerState.geoPoint = it
            },
            depokMarkerState = depokMarkerState
        ) {
            Marker(
                state = depokMarkerState,
                icon = LocalContext.current.getDrawable(R.drawable.ic_location)
            )
        }
    }
}