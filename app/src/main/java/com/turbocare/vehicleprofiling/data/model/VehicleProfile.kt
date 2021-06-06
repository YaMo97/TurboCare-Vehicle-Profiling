package com.turbocare.vehicleprofiling.data.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "vehicle_profiles")
data class VehicleProfile(
    @PrimaryKey var registrationNumber: String = "",
    var vehicleClass: VehicleClass? = null,
    var make: String = "",
    var model: String = "",
    var fuelType: FuelType? = null,
    var transmission: Transmission? = null
) {
    var displayName = "$model $fuelType"
}