package com.turbocare.vehicleprofiling.di

import android.content.Context
import com.turbocare.vehicleprofiling.app.App
import com.turbocare.vehicleprofiling.data.database.LocalVehicleDataSource
import com.turbocare.vehicleprofiling.data.datasource.VehicleDataSource
import com.turbocare.vehicleprofiling.data.network.RemoteVehicleDataSource
import com.turbocare.vehicleprofiling.data.repository.VehicleRepository
import com.turbocare.vehicleprofiling.data.repository.VehicleRepositoryImpl


/**
 * This Class is responsible for providing the dependencies across the modules.
 *
 * This is a simple workaround to a proper Dependency Injection Implementation
 */

class DISingleton(private val applicationContext: Context) {

    init {
        instance = this
    }

    val remoteVehicleDataSource: VehicleDataSource = RemoteVehicleDataSource()
    val localVehicleDataSource: VehicleDataSource = LocalVehicleDataSource()

    val vehicleRepository: VehicleRepository = VehicleRepositoryImpl(
        remoteVehicleDataSource,
        localVehicleDataSource
    )


    companion object {
        private var instance: DISingleton? = null

        fun initialize(applicationContext: Context) {
            synchronized(this) {
                instance = DISingleton(applicationContext)
            }
        }

        fun getInstance(): DISingleton = instance
            ?: throw NullPointerException("Please call initialize() before getting the instance.");
    }


}