package dev.enroute.beacon.helper

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.PowerManager
import android.provider.Settings

val PERMISSIONS = arrayOf(
    Manifest.permission.ACCESS_BACKGROUND_LOCATION,
    Manifest.permission.ACCESS_COARSE_LOCATION,
    Manifest.permission.ACCESS_FINE_LOCATION
)

fun hasBatteryPermissions(context: Context): Boolean {
    val manager =
        context.getSystemService(Context.POWER_SERVICE) as PowerManager
    return manager.isIgnoringBatteryOptimizations(context.packageName)
}

@SuppressLint("BatteryLife")
fun requestBatteryPermissions(context: Context) {
    val intent = Intent()
    intent.action = Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
    intent.data = Uri.parse("package:" + context.packageName)
    context.startActivity(intent)
}

fun hasLocationPermissions(context: Context): Boolean {
    return PERMISSIONS.all { context.checkSelfPermission(it) == PackageManager.PERMISSION_GRANTED }
}

fun requestLocationPermissions(activity: Activity) {
    activity.requestPermissions(PERMISSIONS, 11)
}