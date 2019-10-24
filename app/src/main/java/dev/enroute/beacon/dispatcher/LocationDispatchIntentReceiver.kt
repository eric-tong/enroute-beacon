package dev.enroute.beacon.dispatcher

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import dev.enroute.beacon.api.PlatformService
import timber.log.Timber

class LocationDispatchIntentReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Timber.d("Alarm Run")
        val platformService = PlatformService()
        val locationHelper =
            LocationDispatcher(context!!) { deviceLocation ->
                Timber.d(deviceLocation.toString())
                platformService.onLocationChanged(
                    deviceLocation
                ) { locations ->
                    val lastLocation = locations.last()
                    Toast.makeText(
                        context,
                        lastLocation.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                    Timber.d(lastLocation.toString())
                }
            }
        locationHelper.dispatch()
    }
}