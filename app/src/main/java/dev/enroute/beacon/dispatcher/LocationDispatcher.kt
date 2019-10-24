package dev.enroute.beacon.dispatcher

import android.annotation.SuppressLint
import android.content.Context
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
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
    (private val context: Context, private val onLocationChanged: (Location) -> (Unit)) {

    private var locationManager: LocationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            Timber.d(location.toString())
            onLocationChanged.invoke(location)
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

    @SuppressLint("MissingPermission")
    fun dispatch() {
        if (hasLocationPermissions(context)) {
            locationManager.requestSingleUpdate(criteria, locationListener, null)
        } else {
            Timber.e("Missing location permissions")
        }
    }
}
