package dev.enroute.beacon.api

import android.util.Log
import dev.enroute.beacon.helper.Result
import dev.enroute.beacon.helper.enqueue
import dev.enroute.beacon.model.Location
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.location.Location as DeviceLocation

class PlatformService {
    private val service: PlatformApi = Retrofit.Builder()
        .baseUrl("https://enroute-platform.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create<PlatformApi>(PlatformApi::class.java)

    fun onLocationChanged(location: DeviceLocation?, callback: (List<Location>) -> (Unit)) {
        Log.d("Location", "Location put")
        service.putLocation(location!!.latitude, location.longitude).enqueue { result ->
            when (result) {
                is Result.Success -> {
                    val locations = result.response.body()!!
                    callback(locations)
                    Log.e("locations", locations.toString())
                }
                is Result.Failure -> {
                    Log.e("locations", result.error.toString())
                }
            }
        }
    }
}