package com.turbocare.vehicleprofiling.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.turbocare.vehicleprofiling.data.repository.VehicleRepository
import com.turbocare.vehicleprofiling.ui.profile.create.CreateProfileViewModel
import com.turbocare.vehicleprofiling.ui.profile.details.VehicleProfileViewModel
import com.turbocare.vehicleprofiling.ui.profile.list.VehicleListViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: VehicleRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(CreateProfileViewModel::class.java) -> CreateProfileViewModel(repository) as T
            modelClass.isAssignableFrom(VehicleProfileViewModel::class.java) -> VehicleProfileViewModel(repository) as T
            modelClass.isAssignableFrom(VehicleListViewModel::class.java) -> VehicleListViewModel(repository) as T
            else -> throw IllegalArgumentException("ViewModelClass not found")
        }
    }

}