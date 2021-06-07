package com.turbocare.vehicleprofiling.ui.profile.create.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.turbocare.vehicleprofiling.R
import com.turbocare.vehicleprofiling.databinding.NewVehicleFragmentBinding
import com.turbocare.vehicleprofiling.ui.profile.create.CreateProfileViewModel
import com.turbocare.vehicleprofiling.util.getViewModel

class NewVehicleFragment : Fragment() {

    private lateinit var viewModel: CreateProfileViewModel

    private var binding: NewVehicleFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NewVehicleFragmentBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = requireActivity().getViewModel(CreateProfileViewModel::class.java)

        binding?.nextButton?.setOnClickListener { onNextClicked() }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

    private fun onNextClicked() {
        // Reset error
        binding?.registrationNumberInputField?.error = null

        // Take input
        val input = binding?.registrationNumberInputField?.text.toString()

        // Check if input is valid
        if (isRegistrationNumberValid(input)) {
            viewModel.newVehicleProfile.registrationNumber = input

            navigateToNextStep()
        } else {
            binding?.registrationNumberInputField?.error =
                getString(R.string.error_enter_vehicle_number)
        }

        // If yes store input and navigate to next step
        // If no then show error
    }

    private fun isRegistrationNumberValid(input: String): Boolean {
        return input.isNotEmpty()
    }

    private fun navigateToNextStep() {
        findNavController().navigate(
            NewVehicleFragmentDirections
                .actionNewVehicleFragmentToVehicleClassFragment())
    }

    companion object {
        fun newInstance() = NewVehicleFragment()
    }
}