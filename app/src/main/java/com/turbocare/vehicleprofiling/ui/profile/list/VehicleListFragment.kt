package com.turbocare.vehicleprofiling.ui.profile.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.turbocare.vehicleprofiling.R
import com.turbocare.vehicleprofiling.data.model.VehicleProfile
import com.turbocare.vehicleprofiling.databinding.VehicleListFragmentBinding
import com.turbocare.vehicleprofiling.ui.base.GenericRecyclerViewAdapter

class VehicleListFragment : Fragment() {

    companion object {
        fun newInstance() = VehicleListFragment()
    }

    private var binding: VehicleListFragmentBinding? = null

    private lateinit var viewModel: VehicleListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = VehicleListFragmentBinding.inflate(layoutInflater)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(VehicleListViewModel::class.java)
//
//        val list = listOf(
//            VehicleProfile("i20"),
//            VehicleProfile("Swift")
//        )

        val list = emptyList<VehicleProfile>()

        var adapter = object : GenericRecyclerViewAdapter<VehicleProfile>(list) {
            override fun getLayoutId(position: Int, obj: VehicleProfile): Int =
                R.layout.vehicle_list_fragment

            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder =
                VehicleListViewHolder(view)
        }

        binding?.nextButton?.setOnClickListener { navigateToNextStep() }
        binding?.createButton?.setOnClickListener { navigateToCreateProfile() }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

    private fun navigateToNextStep() {
        findNavController().navigate(
            VehicleListFragmentDirections
                .actionVehicleListFragmentToVehicleProfileFragment())
    }


    private fun navigateToCreateProfile() {
        findNavController().navigate(
            VehicleListFragmentDirections
                .actionVehicleListFragmentToCreateProfileGroup())
    }
}