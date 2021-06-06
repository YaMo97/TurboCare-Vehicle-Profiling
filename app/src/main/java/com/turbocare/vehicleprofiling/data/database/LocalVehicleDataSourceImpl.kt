package com.turbocare.vehicleprofiling.data.database

import android.util.Log
import com.turbocare.vehicleprofiling.data.database.room.AppDatabase
import com.turbocare.vehicleprofiling.data.datasource.LocalVehicleDataSource
import com.turbocare.vehicleprofiling.data.model.VehicleClass
import com.turbocare.vehicleprofiling.data.model.VehicleProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalVehicleDataSourceImpl(
    appDatabase: AppDatabase
): LocalVehicleDataSource {

    private val TAG = LocalVehicleDataSourceImpl::class.simpleName

    private val vehicleDao = appDatabase.vehicleDao()

    override suspend fun getListOfMakes(vehicleClass: VehicleClass): List<String>? {
        return withContext(Dispatchers.IO) {
            try {
                // TODO("Implement in future for Caching")
                null
            } catch (e: Exception) {
                Log.e(TAG, "Exception occurred: ${e.printStackTrace()}")
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
                // TODO("Implement in future for Caching")
                null
            } catch (e: Exception) {
                Log.e(TAG, "Exception occurred: ${e.printStackTrace()}")
                null
            }
        }
    }

    override suspend fun getVehicleProfilesList(): List<VehicleProfile>? {
        return withContext(Dispatchers.IO) {
            try {
                vehicleDao.getAllVehicleProfiles()
            } catch (e: Exception) {
                Log.e(TAG, "Exception occurred: ${e.printStackTrace()}")
                null
            }
        }
    }

    override suspend fun getVehicleProfileDetails(registrationNumber: String): VehicleProfile? {
        return withContext(Dispatchers.IO) {
            try {
                vehicleDao.getVehicleProfile(registrationNumber)
            } catch (e: Exception) {
                Log.e(TAG, "Exception occurred: ${e.printStackTrace()}")
                null
            }
        }
    }


    override suspend fun saveListOfMakes(listOfMakes: List<String>) {
        return withContext(Dispatchers.IO) {
            try {
                // TODO("Implement in future for Caching")
            } catch (e: Exception) {
                Log.e(TAG, "Exception occurred: ${e.printStackTrace()}")
            }
        }
    }

    override suspend fun saveListOfModels(vehicleClass: VehicleClass, listOfModels: List<String>) {
        return withContext(Dispatchers.IO) {
            try {
                // TODO("Implement in future for Caching")
            } catch (e: Exception) {
                Log.e(TAG, "Exception occurred: ${e.printStackTrace()}")
            }
        }
    }

    override suspend fun saveVehicleProfilesList(vehicleProfileList: List<VehicleProfile>) {
        return withContext(Dispatchers.IO) {
            try {
                // TODO("Implement in future for Caching")
            } catch (e: Exception) {
                Log.e(TAG, "Exception occurred: ${e.printStackTrace()}")
            }
        }
    }

    override suspend fun saveVehicleProfileDetails(vehicleProfile: VehicleProfile) {
        return withContext(Dispatchers.IO) {
            try {
                // TODO("Implement in future for Caching")
            } catch (e: Exception) {
                Log.e(TAG, "Exception occurred: ${e.printStackTrace()}")
            }
        }
    }
}