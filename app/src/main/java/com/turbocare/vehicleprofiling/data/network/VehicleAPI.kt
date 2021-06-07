package com.turbocare.vehicleprofiling.data.network

import retrofit2.http.GET
import retrofit2.http.Query

interface VehicleAPI {

    @GET("makes")
    suspend fun getListOfMakes(
        @Query("class") vehicleClass: String
    ): List<String>

    @GET("models")
    suspend fun getListOfModels(
        @Query("class") vehicleClass: String,
        @Query("make") vehicleMake: String
    ): List<String>
}