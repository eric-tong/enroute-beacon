package dev.enroute.beacon.dispatcher

import android.location.Location
import android.location.LocationListener
import android.os.Bundle

class LocationReadyListener(private val onLocationReady: (Location)->(Unit)): LocationListener {
    override fun onLocationChanged(location: Location) {
        onLocationReady.invoke(location)
    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
    override fun onProviderEnabled(provider: String) {}
    override fun onProviderDisabled(provider: String) {}
}