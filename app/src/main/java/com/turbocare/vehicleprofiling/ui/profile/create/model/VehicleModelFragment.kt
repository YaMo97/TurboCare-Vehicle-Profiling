package com.turbocare.vehicleprofiling.ui.profile.create.model

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
import com.turbocare.vehicleprofiling.databinding.VehicleModelFragmentBinding
import com.turbocare.vehicleprofiling.ui.base.BaseRecyclerViewHolder
import com.turbocare.vehicleprofiling.ui.base.GenericRecyclerViewAdapter
import com.turbocare.vehicleprofiling.ui.common.SelectableItemViewHolder
import com.turbocare.vehicleprofiling.ui.profile.create.CreateProfileViewModel
import com.turbocare.vehicleprofiling.util.getViewModel

class VehicleModelFragment : Fragment() {

    private lateinit var viewModel: CreateProfileViewModel

    private var binding: VehicleModelFragmentBinding? = null

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
        binding = VehicleModelFragmentBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = requireActivity().getViewModel(CreateProfileViewModel::class.java)

        binding?.recyclerView?.recyclerView?.let {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = adapter
        }

        getVehicleModels()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

    private fun getVehicleModels() {
        if (viewModel.newVehicleProfile.vehicleClass == null
            || viewModel.newVehicleProfile.make.isEmpty())
                return

        viewModel.getVehicleModels(
            viewModel.newVehicleProfile.vehicleClass!!,
            viewModel.newVehicleProfile.make
        ).observe(viewLifecycleOwner) { listOfMakes ->
            listOfItems = listOfMakes

            adapter.setItems(listOfItems)
        }
    }

    private val itemClickListener = object : BaseRecyclerViewHolder.OnItemClickListener {
        override fun onItemClicked(position: Int) {

            viewModel.newVehicleProfile.model = listOfItems[position]

            Log.d(TAG, "onItemClicked: Vehicle Profile = ${viewModel.newVehicleProfile}")

            navigateToNextStep()
        }
    }

    private fun navigateToNextStep() {
        findNavController().navigate(
            VehicleModelFragmentDirections
                .actionVehicleModelFragmentToVehicleFuelTypeFragment())
    }

    companion object {
        private val TAG = VehicleModelFragment::class.simpleName

        fun newInstance() = VehicleModelFragment()
    }

}