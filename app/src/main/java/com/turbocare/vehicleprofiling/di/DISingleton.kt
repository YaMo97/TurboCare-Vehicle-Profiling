package com.turbocare.vehicleprofiling.di

import android.content.Context
import androidx.room.Room
import com.turbocare.vehicleprofiling.data.database.room.AppDatabase
import com.turbocare.vehicleprofiling.data.database.LocalVehicleDataSourceImpl
import com.turbocare.vehicleprofiling.data.datasource.LocalVehicleDataSource
import com.turbocare.vehicleprofiling.data.datasource.RemoteVehicleDataSource
import com.turbocare.vehicleprofiling.data.network.RemoteVehicleDataSourceImpl
import com.turbocare.vehicleprofiling.data.repository.VehicleRepository
import com.turbocare.vehicleprofiling.data.repository.VehicleRepositoryImpl
import com.turbocare.vehicleprofiling.ui.base.ViewModelFactory


/**
 * This Class is responsible for providing the dependencies across the modules.
 *
 * This is a simple workaround to a proper Dependency Injection Implementation
 */

class DISingleton(applicationContext: Context) {

    init {
        instance = this
    }

    private val appDatabase = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java,
        "vehicles-db"
    )   .fallbackToDestructiveMigration()
        .build()


    private val remoteVehicleDataSource: RemoteVehicleDataSource = RemoteVehicleDataSourceImpl()
    private val localVehicleDataSource: LocalVehicleDataSource = LocalVehicleDataSourceImpl(appDatabase)

    private val vehicleRepository: VehicleRepository = VehicleRepositoryImpl(
        remoteVehicleDataSource,
        localVehicleDataSource
    )

    val viewModelFactory = ViewModelFactory(vehicleRepository)

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