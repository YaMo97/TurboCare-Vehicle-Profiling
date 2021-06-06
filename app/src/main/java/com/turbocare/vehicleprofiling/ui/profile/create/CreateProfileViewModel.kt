package com.turbocare.vehicleprofiling.ui.profile.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turbocare.vehicleprofiling.data.model.VehicleProfile
import com.turbocare.vehicleprofiling.data.repository.VehicleRepository
import kotlinx.coroutines.launch

class CreateProfileViewModel(
    private val vehicleRepository: VehicleRepository
): ViewModel() {

    var newVehicleProfile = VehicleProfile()

    fun saveVehicleProfile(): LiveData<Boolean> {

        val saveProfileLiveData = MutableLiveData<Boolean>()

        viewModelScope.launch {
            val success = vehicleRepository.saveVehicleProfile(vehicleProfile = newVehicleProfile)

            saveProfileLiveData.postValue(success)
        }

        return saveProfileLiveData
    }
}