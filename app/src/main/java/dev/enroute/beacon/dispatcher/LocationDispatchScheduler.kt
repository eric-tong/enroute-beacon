package dev.enroute.beacon.dispatcher

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import timber.log.Timber

class LocationDispatchScheduler(private val context: Context) {
    private val alarmManager: AlarmManager =
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    fun start() {
        LocationDispatcher(context).dispatch()
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis() + AlarmManager.INTERVAL_FIFTEEN_MINUTES,
            getPendingIntent()
        )

        Timber.i("Dispatch scheduled")
    }

    fun stop() {
        alarmManager.cancel(getPendingIntent())

        Timber.i("Dispatch canceled")
    }

    private fun getPendingIntent(): PendingIntent? {
        return PendingIntent.getBroadcast(
            context,
            0,
            Intent(context, LocationDispatchIntentReceiver::class.java),
            0
        )
    }
}

class LocationDispatchIntentReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        LocationDispatchScheduler(context!!).start()
        Timber.i("Dispatch intent received")
    }
}
