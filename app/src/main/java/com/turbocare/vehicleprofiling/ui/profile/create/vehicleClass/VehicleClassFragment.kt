package com.turbocare.vehicleprofiling.ui.profile.create.vehicleClass

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
import com.turbocare.vehicleprofiling.data.model.VehicleClass
import com.turbocare.vehicleprofiling.databinding.VehicleClassFragmentBinding
import com.turbocare.vehicleprofiling.ui.base.BaseRecyclerViewHolder
import com.turbocare.vehicleprofiling.ui.base.GenericRecyclerViewAdapter
import com.turbocare.vehicleprofiling.ui.common.SelectableItemViewHolder
import com.turbocare.vehicleprofiling.ui.profile.create.CreateProfileViewModel
import com.turbocare.vehicleprofiling.util.getViewModel

class VehicleClassFragment : Fragment() {

    private lateinit var viewModel: CreateProfileViewModel

    private var binding: VehicleClassFragmentBinding? = null

    private var listOfItems: List<VehicleClass> = enumValues<VehicleClass>().toList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = VehicleClassFragmentBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = requireActivity().getViewModel(CreateProfileViewModel::class.java)

        binding?.recyclerView?.recyclerView?.let {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = object : GenericRecyclerViewAdapter<VehicleClass>(listOfItems) {
                override fun getLayoutId(position: Int, obj: VehicleClass): Int =
                    R.layout.selectable_list_item

                override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder =
                    SelectableItemViewHolder<VehicleClass>(view, itemClickListener)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

    private val itemClickListener = object : BaseRecyclerViewHolder.OnItemClickListener {
        override fun onItemClicked(position: Int) {

            viewModel.newVehicleProfile.vehicleClass = listOfItems[position]

            Log.d(TAG, "onItemClicked: Vehicle Profile = ${viewModel.newVehicleProfile}")

            navigateToNextStep()
        }
    }

    private fun navigateToNextStep() {
        findNavController().navigate(
            VehicleClassFragmentDirections
                .actionVehicleClassFragmentToVehicleMakeFragment())
    }


    companion object {
        private val TAG = VehicleClassFragment::class.simpleName

        fun newInstance() = VehicleClassFragment()
    }

}