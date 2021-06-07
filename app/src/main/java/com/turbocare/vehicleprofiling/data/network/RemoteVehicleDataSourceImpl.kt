package com.turbocare.vehicleprofiling.data.network

import android.util.Log
import com.turbocare.vehicleprofiling.BuildConfig
import com.turbocare.vehicleprofiling.data.datasource.RemoteVehicleDataSource
import com.turbocare.vehicleprofiling.data.model.VehicleClass
import com.turbocare.vehicleprofiling.data.model.VehicleProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteVehicleDataSourceImpl : RemoteVehicleDataSource {

    override suspend fun getListOfMakes(vehicleClass: VehicleClass): List<String>? {

        return withContext(Dispatchers.IO) {
            try {
                API.getListOfMakes(vehicleClass = vehicleClass.value)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    override suspend fun getListOfModels(
        vehicleClass: VehicleClass,
        vehicleMake: String
    ): List<String>? {

        return withContext(Dispatchers.IO) {
            try {
                API.getListOfModels(vehicleClass = vehicleClass.value, vehicleMake)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }


    override suspend fun getVehicleProfilesList(): List<VehicleProfile>? {
        // Currently returns null / empty list
        // since there's no API implementation for Vehicle profiles

        return null
    }

    override suspend fun getVehicleProfileDetails(profileID: String): VehicleProfile? {
        // Currently returns null / empty list
        // since there's no API implementation for Vehicle profiles

        return null
    }

    override suspend fun saveVehicleProfile(vehicleProfile: VehicleProfile): Boolean {
        // Currently does nothing as there's no such API

        return true // Success
    }



    companion object {

        private const val BASE_URL = "https://test.turbocare.app/turbo/care/v1/"

        val API: VehicleAPI by lazy {
            Retrofit.Builder()
                .client(getOkHttpClient())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(VehicleAPI::class.java)
        }

        private fun getOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder().apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                }
            }.build()
        }
    }
}