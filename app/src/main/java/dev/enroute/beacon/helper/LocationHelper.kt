package dev.enroute.beacon.helper

import android.annotation.SuppressLint
import android.content.Context
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log

class LocationHelper
    (private val context: Context, private val onLocationChanged: (Location) -> (Unit)) {

    private val criteria: Criteria = Criteria()
    private var locationListener: LocationListener? = null
    private var locationManager: LocationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    private var looper: Looper? = null

    init {
        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                Log.d("Location Changes", location.toString())
                onLocationChanged.invoke(location)
            }

            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
                Log.d("Status Changed", status.toString())
            }

            override fun onProviderEnabled(provider: String) {
                Log.d("Provider Enabled", provider)
            }

            override fun onProviderDisabled(provider: String) {
                Log.d("Provider Disabled", provider)
            }
        }

        criteria.accuracy = Criteria.ACCURACY_COARSE
        criteria.powerRequirement = Criteria.POWER_LOW
        criteria.isAltitudeRequired = false
        criteria.isBearingRequired = false
        criteria.isSpeedRequired = false
        criteria.isCostAllowed = true
        criteria.horizontalAccuracy = Criteria.ACCURACY_HIGH
        criteria.verticalAccuracy = Criteria.ACCURACY_HIGH
    }

    @SuppressLint("MissingPermission")
    fun fetchLocationOnce() {
        if (hasLocationPermissions(context))
            locationManager.requestSingleUpdate(criteria, locationListener!!, looper)
        else
            Log.e("LocationHelper", "No relevant permission")
    }
}
