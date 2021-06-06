package com.turbocare.vehicleprofiling.ui.profile.create.registration

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.turbocare.vehicleprofiling.databinding.NewVehicleFragmentBinding

class NewVehicleFragment : Fragment() {

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

        binding?.button?.setOnClickListener { navigateToNextStep() }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
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