package com.example.enroute.beacon.api

import com.example.enroute.beacon.model.Location
import retrofit2.Call
import retrofit2.http.GET

interface EnRoutePlatform {
    @GET("/")
    fun getLocations(): Call<List<Location>>
}