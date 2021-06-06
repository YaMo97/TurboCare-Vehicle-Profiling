package com.turbocare.vehicleprofiling.data.database.room

import androidx.room.*
import com.turbocare.vehicleprofiling.data.model.VehicleProfile

@Dao
interface VehicleProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVehicleProfile(vehicleProfile: VehicleProfile)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMultipleVehicleProfile(vehicleProfiles: List<VehicleProfile>)

    @Query("SELECT * FROM vehicle_profiles WHERE registrationNumber = :registrationNumber")
    suspend fun getVehicleProfile(registrationNumber: String): VehicleProfile

    @Query("SELECT * FROM vehicle_profiles")
    suspend fun getAllVehicleProfiles(): List<VehicleProfile>

    @Delete
    suspend fun deleteVehicleProfile(registrationNumber: String)

    @Query("DELETE FROM vehicle_profiles")
    suspend fun nukeTable()
}