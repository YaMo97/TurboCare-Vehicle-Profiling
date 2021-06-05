package com.turbocare.vehicleprofiling.ui.profile.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.turbocare.vehicleprofiling.R

class VehicleProfileFragment : Fragment() {

    companion object {
        fun newInstance() = VehicleProfileFragment()
    }

    private lateinit var viewModel: VehicleProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.vehicle_profile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(VehicleProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }

}