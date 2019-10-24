package dev.enroute.beacon

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.enroute.beacon.R
import dev.enroute.beacon.helper.Scheduler
import dev.enroute.beacon.helper.hasLocationPermissions
import dev.enroute.beacon.helper.requestLocationPermissions
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val scheduler = Scheduler(this)

        if (!hasLocationPermissions(this)) requestLocationPermissions(this)

        startButton.setOnClickListener {
            scheduler.start(this)
        }
        endButton.setOnClickListener {
            scheduler.stop(this)
        }
    }
}


