package com.example.enroute.beacon

import retrofit2.Call
import retrofit2.http.GET

interface EnRoutePlatform {
    @GET("/")
    fun getLocations(): Call<List<Location>>
}