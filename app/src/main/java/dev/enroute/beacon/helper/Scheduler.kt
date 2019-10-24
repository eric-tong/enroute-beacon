package dev.enroute.beacon.helper

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import dev.enroute.beacon.api.PlatformService

class Scheduler(context: Context) {
    private val pendingIntent: PendingIntent =
        PendingIntent.getBroadcast(context, 0, Intent(context, Receiver::class.java), 0)
    private val manager: AlarmManager =
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    fun start(context: Context) {
        val interval = 60000L

        manager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis(),
            interval,
            pendingIntent
        )
        Toast.makeText(context, "Alarm Set", Toast.LENGTH_SHORT).show()
        Log.e("Scheduler", "Alarm Set")
    }

    fun stop(context: Context) {
        manager.cancel(pendingIntent)
        Toast.makeText(context, "Alarm Canceled", Toast.LENGTH_SHORT).show()
        Log.e("Scheduler", "Alarm Canceled")
    }
}

class Receiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e("Scheduler", "Alarm Run")
        val platformService = PlatformService()
        val locationHelper =
            LocationHelper(context!!) { deviceLocation ->
                Log.e("Scheduler", deviceLocation.toString())
                platformService.onLocationChanged(
                    deviceLocation
                ) { locations ->
                    val lastLocation = locations.last()
                    Toast.makeText(context, lastLocation.toString(), Toast.LENGTH_SHORT).show()
                    Log.e("Scheduler", lastLocation.toString())
                }
            }
        locationHelper.fetchLocationOnce()
    }
}