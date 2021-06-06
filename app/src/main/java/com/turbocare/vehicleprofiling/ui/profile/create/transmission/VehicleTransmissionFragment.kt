package com.turbocare.vehicleprofiling.ui.profile.create.transmission

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.turbocare.vehicleprofiling.R
import com.turbocare.vehicleprofiling.data.model.Transmission
import com.turbocare.vehicleprofiling.databinding.VehicleModelFragmentBinding
import com.turbocare.vehicleprofiling.databinding.VehicleTransmissionFragmentBinding
import com.turbocare.vehicleprofiling.ui.profile.create.CreateProfileViewModel
import com.turbocare.vehicleprofiling.ui.profile.create.model.VehicleModelFragmentDirections

class VehicleTransmissionFragment : Fragment() {

    companion object {
        fun newInstance() = VehicleTransmissionFragment()
    }

    private lateinit var viewModel: CreateProfileViewModel

    private var binding: VehicleTransmissionFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = VehicleTransmissionFragmentBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(CreateProfileViewModel::class.java)

        binding?.button?.setOnClickListener { onItemSelected(Transmission.MANUAL) }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

    private fun onItemSelected(selectedItem: Transmission) {
        viewModel.newVehicleProfile.transmission = selectedItem

        saveVehicleProfile()
    }

    private fun saveVehicleProfile() {
        viewModel.saveVehicleProfile().observe(viewLifecycleOwner, { success ->
            if (success)
                navigateToVehicleProfileFragment(viewModel.newVehicleProfile.registrationNumber)
            else
                Toast.makeText(context, "Save Vehicle Profile Failed!", Toast.LENGTH_SHORT).show()
        })
    }

    private fun navigateToVehicleProfileFragment(registrationNumber: String) {
        findNavController().navigate(
            VehicleTransmissionFragmentDirections
                .actionGlobalVehicleProfileFragment(registrationNumber))
    }

}