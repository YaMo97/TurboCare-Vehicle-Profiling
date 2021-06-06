package com.turbocare.vehicleprofiling.data.datasource

import com.turbocare.vehicleprofiling.data.model.VehicleClass
import com.turbocare.vehicleprofiling.data.model.VehicleProfile

interface RemoteVehicleDataSource {

    suspend fun getListOfMakes(vehicleClass: VehicleClass): List<String>?

    suspend fun getListOfModels(vehicleClass: VehicleClass, vehicleMake: String): List<String>?

    // Currently Returns Null
    suspend fun getVehicleProfilesList(): List<VehicleProfile>?

    // Currently Returns Null
    suspend fun getVehicleProfileDetails(profileID: String): VehicleProfile?
}