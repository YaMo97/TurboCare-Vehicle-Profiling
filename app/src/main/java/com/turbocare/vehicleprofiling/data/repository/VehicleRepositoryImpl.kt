package com.turbocare.vehicleprofiling.data.repository

import com.turbocare.vehicleprofiling.data.datasource.LocalVehicleDataSource
import com.turbocare.vehicleprofiling.data.datasource.RemoteVehicleDataSource
import com.turbocare.vehicleprofiling.data.model.VehicleClass
import com.turbocare.vehicleprofiling.data.model.VehicleProfile

class VehicleRepositoryImpl(
    private val remoteVehicleDataSource: RemoteVehicleDataSource,
    private val localVehicleDataSource: LocalVehicleDataSource
): VehicleRepository {

    override suspend fun getListOfMakes(vehicleClass: VehicleClass): List<String> {
        return localVehicleDataSource.getListOfMakes(vehicleClass)
            ?: remoteVehicleDataSource.getListOfMakes(vehicleClass)
                ?.also { localVehicleDataSource.saveListOfMakes(it) }
            ?: emptyList()
    }

    override suspend fun getListOfModels(
        vehicleClass: VehicleClass,
        vehicleMake: String
    ): List<String> {
        return localVehicleDataSource.getListOfModels(vehicleClass, vehicleMake)
            ?: remoteVehicleDataSource.getListOfModels(vehicleClass, vehicleMake)
                ?.also { localVehicleDataSource.saveListOfModels(vehicleClass, it) }
            ?: emptyList()
    }

    override suspend fun getVehicleProfilesList(): List<VehicleProfile> {
        return localVehicleDataSource.getVehicleProfilesList()
            ?: remoteVehicleDataSource.getVehicleProfilesList()
                ?.also { localVehicleDataSource.saveVehicleProfilesList(it) }
            ?: emptyList()
    }

    override suspend fun getVehicleProfileDetails(registrationNumber: String): VehicleProfile? {
        return localVehicleDataSource.getVehicleProfileDetails(registrationNumber)
            ?: remoteVehicleDataSource.getVehicleProfileDetails(registrationNumber)
                ?.also { localVehicleDataSource.saveVehicleProfileDetails(it) }
    }
}