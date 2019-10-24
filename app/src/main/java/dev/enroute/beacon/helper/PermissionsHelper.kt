package dev.enroute.beacon.helper

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager

val PERMISSIONS = arrayOf(
    Manifest.permission.ACCESS_BACKGROUND_LOCATION,
    Manifest.permission.ACCESS_COARSE_LOCATION,
    Manifest.permission.ACCESS_FINE_LOCATION
)

fun hasLocationPermissions(context: Context): Boolean {
    return PERMISSIONS.all { context.checkSelfPermission(it) != PackageManager.PERMISSION_GRANTED }
}

fun requestLocationPermissions(activity: Activity) {
    activity.requestPermissions(PERMISSIONS, 11)
}