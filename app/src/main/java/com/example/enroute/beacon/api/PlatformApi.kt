package com.example.enroute.beacon.api

import com.example.enroute.beacon.model.Location
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlatformApi {
    @GET("/")
    fun getLocations(): Call<List<Location>>

    @GET("new")
    fun putLocation(@Query("lat") lat: Number, @Query("long") long: Number): Call<List<Location>>
}