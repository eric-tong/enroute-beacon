package dev.enroute.beacon.api

import dev.enroute.beacon.api.model.LocationEntry
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PlatformApi {
    @GET("/")
    fun getLocations(): Call<List<LocationEntry>>

    @GET("new")
    fun putLocation(@Query("lat") lat: Number, @Query("long") long: Number): Call<List<LocationEntry>>
}

internal val platformApiService: PlatformApi = Retrofit.Builder()
    .baseUrl("https://enroute-platform.herokuapp.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create<PlatformApi>(PlatformApi::class.java)