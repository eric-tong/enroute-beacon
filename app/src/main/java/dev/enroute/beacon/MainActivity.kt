package dev.enroute.beacon

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.enroute.beacon.R
import dev.enroute.beacon.api.PlatformApi
import dev.enroute.beacon.helper.Result
import dev.enroute.beacon.helper.enqueue
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), LocationListener {
    private val service: PlatformApi = Retrofit.Builder()
        .baseUrl("https://enroute-platform.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create<PlatformApi>(PlatformApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val locationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), 11
            )
        }
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 11
            )
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0F, this)
    }

    override fun onLocationChanged(location: Location?) {
        service.putLocation(location!!.latitude, location.longitude).enqueue { result ->
            when (result) {
                is Result.Success -> {
                    val locations = result.response.body()
                    Log.e("locations", locations.toString())
                }
                is Result.Failure -> {
                    Log.e("locations", result.error.toString())
                }
            }
        }
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderEnabled(provider: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderDisabled(provider: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}


