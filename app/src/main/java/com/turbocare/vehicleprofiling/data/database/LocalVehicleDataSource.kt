package com.turbocare.vehicleprofiling.data.database

import com.turbocare.vehicleprofiling.data.datasource.VehicleDataSource
import com.turbocare.vehicleprofiling.data.model.VehicleClass
import com.turbocare.vehicleprofiling.data.model.VehicleProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalVehicleDataSource: VehicleDataSource {

    override suspend fun getListOfMakes(vehicleClass: VehicleClass): List<String>? {
        return withContext(Dispatchers.IO) {
            try {
                TODO("Not yet implemented")
            } catch (e: Exception) {
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
                TODO("Not yet implemented")
            } catch (e: Exception) {
                null
            }
        }
    }

    override suspend fun getVehicleProfilesList(): List<VehicleProfile>? {
        return withContext(Dispatchers.IO) {
            try {
                TODO("Not yet implemented")
            } catch (e: Exception) {
                null
            }
        }
    }

    override suspend fun getVehicleProfileDetails(profileID: String): VehicleProfile? {
        return withContext(Dispatchers.IO) {
            try {
                TODO("Not yet implemented")
            } catch (e: Exception) {
                null
            }
        }
    }
}