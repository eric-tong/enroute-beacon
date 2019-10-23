package dev.enroute.beacon

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.enroute.beacon.R
import dev.enroute.beacon.api.PlatformApi
import dev.enroute.beacon.helper.enqueue
import dev.enroute.beacon.helper.Result
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service = Retrofit.Builder()
            .baseUrl("https://enroute-platform.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create<PlatformApi>(PlatformApi::class.java)

        val lat = 444F
        val long = 444F

        service.putLocation(lat, long).enqueue { result ->
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
}


