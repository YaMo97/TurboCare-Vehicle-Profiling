package com.turbocare.vehicleprofiling.ui.profile.create.model

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.turbocare.vehicleprofiling.R
import com.turbocare.vehicleprofiling.databinding.VehicleMakeFragmentBinding
import com.turbocare.vehicleprofiling.databinding.VehicleModelFragmentBinding
import com.turbocare.vehicleprofiling.ui.profile.create.make.VehicleMakeFragmentDirections

class VehicleModelFragment : Fragment() {

    companion object {
        fun newInstance() = VehicleModelFragment()
    }

    private var binding: VehicleModelFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = VehicleModelFragmentBinding.inflate(inflater, container, false)

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
            VehicleModelFragmentDirections
                .actionVehicleModelFragmentToVehicleFuelTypeFragment())
    }

}