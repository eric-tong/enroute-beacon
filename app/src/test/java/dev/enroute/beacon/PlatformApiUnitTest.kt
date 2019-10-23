package dev.enroute.beacon

import dev.enroute.beacon.api.PlatformApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory.create

class PlatformApiUnitTest {
    private val service = Retrofit.Builder()
        .baseUrl("https://enroute-platform.herokuapp.com/")
        .addConverterFactory(create())
        .build()
        .create<PlatformApi>(PlatformApi::class.java)

    @Test
    fun getsLocationsFromServer() {
        val locations = service.getLocations().execute().body()

        assertFalse(locations!!.isEmpty())
    }

    @Test
    fun putsSingleLocationOntoServer() {
        val lat = 100F
        val long = 200F

        val locations = service.putLocation(lat, long).execute().body()
        val lastLocation = locations!!.last()

        assertEquals(lastLocation.coords.x, lat)
        assertEquals(lastLocation.coords.y, long)
    }
}
