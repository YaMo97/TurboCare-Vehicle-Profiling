package com.turbocare.vehicleprofiling.data.model

import androidx.annotation.Keep

@Keep
data class VehicleProfile(
    var registrationNumber: String,
    var vehicleClass: VehicleClass,
    var make: String,
    var model: String,
    var fuelType: FuelType,
    var transmission: Transmission
) {
    var displayName = "$model $fuelType"
}