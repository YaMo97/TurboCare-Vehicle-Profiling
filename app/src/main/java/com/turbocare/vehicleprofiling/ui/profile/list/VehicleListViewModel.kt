package com.turbocare.vehicleprofiling.ui.profile.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turbocare.vehicleprofiling.data.model.VehicleProfile
import com.turbocare.vehicleprofiling.data.repository.VehicleRepository
import kotlinx.coroutines.launch

class VehicleListViewModel(private val vehicleRepository: VehicleRepository) : ViewModel() {

    private val vehicleListLiveData = MutableLiveData<List<VehicleProfile>>()

    fun refreshVehicleList() {
        viewModelScope.launch {
            vehicleListLiveData.postValue(vehicleRepository.getVehicleProfilesList())
        }
    }

    private fun getVehicleListLiveData() : LiveData<List<VehicleProfile>> =
        vehicleListLiveData
}