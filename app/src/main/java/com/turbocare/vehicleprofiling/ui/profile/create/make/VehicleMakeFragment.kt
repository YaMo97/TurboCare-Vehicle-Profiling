package com.turbocare.vehicleprofiling.ui.profile.create.make

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.turbocare.vehicleprofiling.R
import com.turbocare.vehicleprofiling.databinding.VehicleMakeFragmentBinding
import com.turbocare.vehicleprofiling.ui.base.BaseRecyclerViewHolder
import com.turbocare.vehicleprofiling.ui.base.GenericRecyclerViewAdapter
import com.turbocare.vehicleprofiling.ui.common.SelectableItemViewHolder
import com.turbocare.vehicleprofiling.ui.profile.create.CreateProfileViewModel
import com.turbocare.vehicleprofiling.util.getViewModel

class VehicleMakeFragment : Fragment() {

    private lateinit var viewModel: CreateProfileViewModel

    private var binding: VehicleMakeFragmentBinding? = null

    private var listOfItems: List<String> = emptyList()

    private var adapter: GenericRecyclerViewAdapter<String> = object : GenericRecyclerViewAdapter<String>() {
        override fun getLayoutId(position: Int, obj: String): Int =
            R.layout.selectable_list_item

        override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder =
            SelectableItemViewHolder<String>(view, itemClickListener)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = VehicleMakeFragmentBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = requireActivity().getViewModel(CreateProfileViewModel::class.java)

        binding?.recyclerView?.recyclerView?.let {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = adapter
        }

        getVehicleMakes()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

    private fun getVehicleMakes() {
        viewModel.newVehicleProfile.vehicleClass?.let { vehicleClass ->
            viewModel.getVehicleMakes(vehicleClass)
                .observe(viewLifecycleOwner) { listOfMakes ->
                    Log.d(TAG, "getVehicleMakes: $listOfMakes")
                    listOfItems = listOfMakes

                    adapter.setItems(listOfItems)
                }
        }
    }

    private val itemClickListener = object : BaseRecyclerViewHolder.OnItemClickListener {
        override fun onItemClicked(position: Int) {

            viewModel.newVehicleProfile.make = listOfItems[position]

            Log.d(TAG, "onItemClicked: Vehicle Profile = ${viewModel.newVehicleProfile}")

            navigateToNextStep()
        }
    }

    private fun navigateToNextStep() {
        findNavController().navigate(
            VehicleMakeFragmentDirections
                .actionVehicleMakeFragmentToVehicleModelFragment())
    }


    companion object {
        private val TAG = VehicleMakeFragment::class.simpleName

        fun newInstance() = VehicleMakeFragment()
    }
}