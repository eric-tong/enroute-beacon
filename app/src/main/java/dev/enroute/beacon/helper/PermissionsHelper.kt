package dev.enroute.beacon.helper

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager

fun hasLocationPermissions(context: Context): Boolean {
    return (context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) &&
            (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
}

fun requestLocationPermissions(activity: Activity) {
    activity.requestPermissions(
        arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        ), 11
    )
}