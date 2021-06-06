package com.turbocare.vehicleprofiling.data.repository

import com.turbocare.vehicleprofiling.data.model.VehicleClass
import com.turbocare.vehicleprofiling.data.model.VehicleProfile

interface VehicleRepository {

    suspend fun getListOfMakes(vehicleClass: VehicleClass): List<String>

    suspend fun getListOfModels(vehicleClass: VehicleClass, vehicleMake: String): List<String>

    suspend fun getVehicleProfilesList(): List<VehicleProfile>

    suspend fun getVehicleProfileDetails(registrationNumber: String): VehicleProfile?
}