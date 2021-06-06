package com.turbocare.vehicleprofiling.data.repository

import com.turbocare.vehicleprofiling.data.datasource.VehicleDataSource
import com.turbocare.vehicleprofiling.data.model.VehicleClass
import com.turbocare.vehicleprofiling.data.model.VehicleProfile

class VehicleRepositoryImpl(
    private val remoteVehicleDataSource: VehicleDataSource,
    private val localVehicleDataSource: VehicleDataSource
): VehicleRepository {

    override suspend fun getListOfMakes(vehicleClass: VehicleClass): List<String> {
        return localVehicleDataSource.getListOfMakes(vehicleClass)
            ?: remoteVehicleDataSource.getListOfMakes(vehicleClass)
            ?: emptyList()
    }

    override suspend fun getListOfModels(
        vehicleClass: VehicleClass,
        vehicleMake: String
    ): List<String> {
        return localVehicleDataSource.getListOfModels(vehicleClass, vehicleMake)
            ?: remoteVehicleDataSource.getListOfModels(vehicleClass, vehicleMake)
            ?: emptyList()
    }

    override suspend fun getVehicleProfilesList(): List<VehicleProfile> {
        return localVehicleDataSource.getVehicleProfilesList()
            ?: remoteVehicleDataSource.getVehicleProfilesList()
            ?: emptyList()
    }

    override suspend fun getVehicleProfileDetails(profileID: String): VehicleProfile? {
        return localVehicleDataSource.getVehicleProfileDetails(profileID)
            ?: remoteVehicleDataSource.getVehicleProfileDetails(profileID)
    }
}