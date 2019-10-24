package dev.enroute.beacon.helper

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import dev.enroute.beacon.api.PlatformService
import timber.log.Timber

class Scheduler(context: Context) {
    private val pendingIntent: PendingIntent =
        PendingIntent.getBroadcast(context, 0, Intent(context, Receiver::class.java), 0)
    private val manager: AlarmManager =
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    fun start(context: Context) {
        manager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis(),
            AlarmManager.INTERVAL_FIFTEEN_MINUTES,
            pendingIntent
        )
        Toast.makeText(context, "Alarm Set", Toast.LENGTH_SHORT).show()
        Timber.d("Alarm Set")
    }

    fun stop(context: Context) {
        manager.cancel(pendingIntent)
        Toast.makeText(context, "Alarm Canceled", Toast.LENGTH_SHORT).show()
        Timber.d("Alarm Canceled")
    }
}

class Receiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Timber.d("Alarm Run")
        val platformService = PlatformService()
        val locationHelper =
            LocationHelper(context!!) { deviceLocation ->
                Timber.d(deviceLocation.toString())
                platformService.onLocationChanged(
                    deviceLocation
                ) { locations ->
                    val lastLocation = locations.last()
                    Toast.makeText(context, lastLocation.toString(), Toast.LENGTH_SHORT).show()
                    Timber.d(lastLocation.toString())
                }
            }
        locationHelper.fetchLocationOnce()
    }
}