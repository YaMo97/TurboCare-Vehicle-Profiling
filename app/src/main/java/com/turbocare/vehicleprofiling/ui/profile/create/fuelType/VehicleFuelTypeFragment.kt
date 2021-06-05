package com.turbocare.vehicleprofiling.ui.profile.create.fuelType

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.turbocare.vehicleprofiling.R
import com.turbocare.vehicleprofiling.databinding.NewVehicleFragmentBinding
import com.turbocare.vehicleprofiling.databinding.VehicleFuelTypeFragmentBinding
import com.turbocare.vehicleprofiling.ui.profile.create.registration.NewVehicleFragmentDirections
import com.turbocare.vehicleprofiling.ui.profile.create.registration.NewVehicleViewModel

class VehicleFuelTypeFragment : Fragment() {

    companion object {
        fun newInstance() = VehicleFuelTypeFragment()
    }

    private var binding: VehicleFuelTypeFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = VehicleFuelTypeFragmentBinding.inflate(inflater, container, false)

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
            VehicleFuelTypeFragmentDirections
                .actionVehicleFuelTypeFragmentToVehicleTransmissionFragment())
    }

}