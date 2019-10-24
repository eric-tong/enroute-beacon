package dev.enroute.beacon.dispatcher

import android.annotation.SuppressLint
import android.content.Context
import android.location.Criteria
import android.location.Location
import android.location.LocationManager
import dev.enroute.beacon.api.platformApiService
import dev.enroute.beacon.helper.Result
import dev.enroute.beacon.helper.enqueue
import dev.enroute.beacon.helper.hasLocationPermissions
import timber.log.Timber

val criteria: Criteria = Criteria().apply {
    accuracy = Criteria.ACCURACY_COARSE
    powerRequirement = Criteria.POWER_LOW
    isAltitudeRequired = false
    isBearingRequired = false
    isSpeedRequired = false
    isCostAllowed = true
    horizontalAccuracy = Criteria.ACCURACY_HIGH
    verticalAccuracy = Criteria.ACCURACY_HIGH
}

class LocationDispatcher
    (private val context: Context) {

    private var locationManager: LocationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    @SuppressLint("MissingPermission")
    fun dispatch() {
        if (hasLocationPermissions(context)) {
            locationManager.requestSingleUpdate(
                criteria,
                LocationReadyListener { onLocationReady(it) },
                null
            )
        } else {
            Timber.e("Missing location permissions")
        }
    }
    
    private fun onLocationReady(location: Location) {
        platformApiService.putLocation(location.latitude, location.longitude).enqueue { result ->
            when (result) {
                is Result.Success -> {
                }
                is Result.Failure -> {
                }
            }
        }
    }
}
