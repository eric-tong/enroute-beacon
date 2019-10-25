package dev.enroute.beacon

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.enroute.beacon.R
import dev.enroute.beacon.dispatcher.LocationDispatchScheduler
import dev.enroute.beacon.helper.hasBatteryPermissions
import dev.enroute.beacon.helper.hasLocationPermissions
import dev.enroute.beacon.helper.requestBatteryPermissions
import dev.enroute.beacon.helper.requestLocationPermissions
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val scheduler = LocationDispatchScheduler(this)

        if (!hasLocationPermissions(this)) requestLocationPermissions(this)
        if (!hasBatteryPermissions(this)) requestBatteryPermissions(this)

        startButton.setOnClickListener {
            scheduler.start()
        }
        endButton.setOnClickListener {
            scheduler.stop()
        }
    }
}


