package dev.enroute.beacon.dispatcher

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.Toast
import timber.log.Timber

class LocationDispatchScheduler(context: Context) {
    private val pendingIntent: PendingIntent =
        PendingIntent.getBroadcast(
            context,
            0,
            Intent(context, LocationDispatchIntentReceiver::class.java),
            0
        )
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

