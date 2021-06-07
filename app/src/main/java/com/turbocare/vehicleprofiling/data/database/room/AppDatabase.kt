package com.turbocare.vehicleprofiling.data.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.turbocare.vehicleprofiling.data.model.VehicleProfile

@Database(entities = [VehicleProfile::class], version = 2, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun vehicleDao(): VehicleProfileDao
}