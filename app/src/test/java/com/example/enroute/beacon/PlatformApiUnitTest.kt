package com.example.enroute.beacon

import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory.create

class PlatformApiUnitTest {
    @Test
    fun getsLocationsFromServer() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://enroute-platform.herokuapp.com/")
            .addConverterFactory(create())
            .build()
        val service = retrofit.create<EnRoutePlatform>(EnRoutePlatform::class.java)
        val locations = service.getLocations().execute().body()

        println(locations)
    }
}
