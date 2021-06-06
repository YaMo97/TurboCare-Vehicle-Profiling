package com.turbocare.vehicleprofiling.data.datasource

import com.turbocare.vehicleprofiling.data.model.VehicleClass
import com.turbocare.vehicleprofiling.data.model.VehicleProfile

interface LocalVehicleDataSource {

    suspend fun getListOfMakes(vehicleClass: VehicleClass): List<String>?

    suspend fun saveListOfMakes(listOfMakes: List<String>)

    suspend fun getListOfModels(vehicleClass: VehicleClass, vehicleMake: String): List<String>?

    suspend fun saveListOfModels(vehicleClass: VehicleClass, listOfModels: List<String>)

    suspend fun getVehicleProfilesList(): List<VehicleProfile>?

    suspend fun saveVehicleProfilesList(vehicleProfileList: List<VehicleProfile>): Boolean

    suspend fun getVehicleProfileDetails(registrationNumber: String): VehicleProfile?

    suspend fun saveVehicleProfileDetails(vehicleProfile: VehicleProfile): Boolean
}