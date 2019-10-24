package dev.enroute.beacon

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.enroute.beacon.R
import dev.enroute.beacon.api.PlatformService
import dev.enroute.beacon.helper.LocationHelper
import dev.enroute.beacon.helper.hasLocationPermissions
import dev.enroute.beacon.helper.requestLocationPermissions
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!hasLocationPermissions(this)) requestLocationPermissions(this)

        val platformService = PlatformService()
        val locationHelper =
            LocationHelper(this) { deviceLocation ->
                platformService.onLocationChanged(
                    deviceLocation
                ) { location -> helloWorld.text = location.toString() }
            }

        sendButton.setOnClickListener {
            locationHelper.fetchLocationOnce()
        }
    }
}


