package com.turbocare.vehicleprofiling.ui.profile.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.turbocare.vehicleprofiling.R
import com.turbocare.vehicleprofiling.databinding.VehicleProfileFragmentBinding
import com.turbocare.vehicleprofiling.ui.MainActivity
import com.turbocare.vehicleprofiling.util.getViewModel

class VehicleProfileFragment : Fragment() {

    private var binding: VehicleProfileFragmentBinding? = null

    private lateinit var viewModel: VehicleProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = VehicleProfileFragmentBinding.inflate(inflater, container, false)

        binding?.makeLayout?.title?.text = getString(R.string.make_label)
        binding?.modelLayout?.title?.text = getString(R.string.model_label)
        binding?.fuelTypeLayout?.title?.text = getString(R.string.fuel_type_label)
        binding?.transmissionLayout?.title?.text = getString(R.string.transmission_label)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel(VehicleProfileViewModel::class.java)

        setupObservers()

        val registrationNumber = arguments?.getString(PARAM_REGISTRATION_NUMBER)
        registrationNumber?.let {
            viewModel.getVehicleProfile(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

    private fun setupObservers() {
        viewModel.getVehicleProfileLiveData().observe(viewLifecycleOwner, { vehicleProfile ->

            vehicleProfile?.run {

                (activity as MainActivity?)?.binding?.appBarLayout?.let {

                    Log.d("Toolbar", "setupObservers: Setting Title to ${getDisplayName().uppercase()}")

                    it.collapsingToolbarLayout.title = getDisplayName().uppercase()
                    it.registrationNumberSubtitle.text = registrationNumber
                }

                binding?.makeLayout?.value?.text = make
                binding?.modelLayout?.value?.text = model
                binding?.fuelTypeLayout?.value?.text = fuelType?.displayString
                binding?.transmissionLayout?.value?.text = transmission?.displayString
            }
        })
    }

    companion object {

        const val PARAM_REGISTRATION_NUMBER = "registration-number"

        fun newInstance(registrationNumber: String) = VehicleProfileFragment().apply {
            arguments = Bundle().apply {
                putString(PARAM_REGISTRATION_NUMBER, registrationNumber)
            }
        }
    }
}