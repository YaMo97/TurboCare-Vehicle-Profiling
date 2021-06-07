package com.turbocare.vehicleprofiling.ui.profile.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.turbocare.vehicleprofiling.R
import com.turbocare.vehicleprofiling.data.model.VehicleProfile
import com.turbocare.vehicleprofiling.databinding.VehicleListFragmentBinding
import com.turbocare.vehicleprofiling.ui.base.BaseRecyclerViewHolder
import com.turbocare.vehicleprofiling.ui.base.GenericRecyclerViewAdapter
import com.turbocare.vehicleprofiling.util.getViewModel

class VehicleListFragment : Fragment() {

    private lateinit var viewModel: VehicleListViewModel

    private var binding: VehicleListFragmentBinding? = null

    private var listOfItems = emptyList<VehicleProfile>()

    private var adapter =  object : GenericRecyclerViewAdapter<VehicleProfile>() {
        override fun getLayoutId(position: Int, obj: VehicleProfile): Int =
            R.layout.vehicle_list_item

        override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder =
            VehicleListViewHolder(view, itemClickListener)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = VehicleListFragmentBinding.inflate(layoutInflater)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel(VehicleListViewModel::class.java)

        setupObservers()

        binding?.createProfileButton?.setOnClickListener { navigateToCreateProfile() }
        binding?.recyclerView?.recyclerView?.let {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = adapter
        }

        viewModel.refreshVehicleList()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }


    private val itemClickListener = object : BaseRecyclerViewHolder.OnItemClickListener {
        override fun onItemClicked(position: Int) {

            val vehicleProfile: VehicleProfile = listOfItems[position]

            Log.d(TAG, "onItemClicked: Vehicle Profile = $vehicleProfile")

            navigateToVehicleProfileFragment(vehicleProfile.registrationNumber)
        }
    }

    private fun setupObservers() {
        viewModel.getVehicleListLiveData().observe(viewLifecycleOwner) { listOfVehicles ->
            listOfItems = listOfVehicles

            adapter.setItems(listOfItems)

            if (listOfVehicles.isEmpty()) {
                binding?.recyclerView?.recyclerView?.visibility = View.GONE
                binding?.noItemsWarning?.visibility = View.VISIBLE
            } else {
                binding?.recyclerView?.recyclerView?.visibility = View.VISIBLE
                binding?.noItemsWarning?.visibility = View.GONE
            }
        }
    }

    private fun navigateToVehicleProfileFragment(selectedRegistrationNumber: String) {
        findNavController().navigate(
            VehicleListFragmentDirections
                .actionVehicleListFragmentToVehicleProfileFragment(selectedRegistrationNumber))
    }


    private fun navigateToCreateProfile() {
        findNavController().navigate(
            VehicleListFragmentDirections
                .actionVehicleListFragmentToCreateProfileGroup())
    }

    companion object {
        private val TAG = VehicleListFragment::class.simpleName

        fun newInstance() = VehicleListFragment()
    }
}