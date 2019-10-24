package dev.enroute.beacon

import android.app.Application
import com.example.enroute.beacon.BuildConfig
import timber.log.Timber

class Application: Application() {
    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}