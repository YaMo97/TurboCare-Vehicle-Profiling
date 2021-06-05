package com.turbocare.vehicleprofiling.ui.profile.create.vehicleClass

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.turbocare.vehicleprofiling.R
import com.turbocare.vehicleprofiling.databinding.VehicleClassFragmentBinding
import com.turbocare.vehicleprofiling.databinding.VehicleTransmissionFragmentBinding
import com.turbocare.vehicleprofiling.ui.profile.create.transmission.VehicleTransmissionFragmentDirections

class VehicleClassFragment : Fragment() {

    companion object {
        fun newInstance() = VehicleClassFragment()
    }

    private var binding: VehicleClassFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = VehicleClassFragmentBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.button?.setOnClickListener { navigateToNextStep() }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

    private fun navigateToNextStep() {
        findNavController().navigate(
            VehicleClassFragmentDirections
                .actionVehicleClassFragmentToVehicleMakeFragment())
    }

}