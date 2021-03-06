package com.turbocare.vehicleprofiling.data.model

enum class VehicleClass(
    var displayString: String,
    var value: String
) {
    TWO_WHEELER("Two Wheeler", "2w"),
    FOUR_WHEELER("Four Wheeler", "4w");

    override fun toString() = displayString
}

enum class Transmission(
    var displayString: String
) {
    MANUAL("Manual"),
    AUTOMATIC("Automatic");

    override fun toString() = displayString
}

enum class FuelType(
    var displayString: String
) {
    PETROL("Petrol"),
    DIESEL("Diesel"),
    CNG("CNG"),
    PETROL_CNG("Petrol + CNG"),
    ELECTRIC("Electric"),
    HYBRID("Hybrid");

    override fun toString() = displayString
}
