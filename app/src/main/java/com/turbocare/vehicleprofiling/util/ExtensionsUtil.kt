package com.turbocare.vehicleprofiling.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.turbocare.vehicleprofiling.di.DISingleton


fun <T: ViewModel> ViewModelStoreOwner.getViewModel(modelClass: Class<T>): T {

    return ViewModelProvider(this, DISingleton.getInstance().viewModelFactory)
        .get(modelClass)
}
