package com.turbocare.vehicleprofiling.ui.profile.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turbocare.vehicleprofiling.data.model.VehicleProfile
import com.turbocare.vehicleprofiling.data.repository.VehicleRepository
import kotlinx.coroutines.launch

class VehicleProfileViewModel(
    private val vehicleRepository: VehicleRepository
) : ViewModel() {

    private val vehicleProfileLiveData = MutableLiveData<VehicleProfile>()

    fun getVehicleProfileLiveData(): LiveData<VehicleProfile> = vehicleProfileLiveData

    fun getVehicleProfile(registrationNumber: String) {
        viewModelScope.launch {
            vehicleProfileLiveData.postValue(
                vehicleRepository.getVehicleProfileDetails(registrationNumber)
            )
        }
    }
}