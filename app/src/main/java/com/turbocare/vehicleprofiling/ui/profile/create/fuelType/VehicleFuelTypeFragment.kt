package com.turbocare.vehicleprofiling.ui.profile.create.fuelType

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.turbocare.vehicleprofiling.R
import com.turbocare.vehicleprofiling.data.model.FuelType
import com.turbocare.vehicleprofiling.data.model.VehicleProfile
import com.turbocare.vehicleprofiling.databinding.VehicleFuelTypeFragmentBinding
import com.turbocare.vehicleprofiling.ui.base.BaseRecyclerViewHolder
import com.turbocare.vehicleprofiling.ui.base.GenericRecyclerViewAdapter
import com.turbocare.vehicleprofiling.ui.common.SelectableItemViewHolder
import com.turbocare.vehicleprofiling.ui.profile.create.CreateProfileViewModel
import com.turbocare.vehicleprofiling.ui.profile.list.VehicleListViewHolder
import com.turbocare.vehicleprofiling.util.getViewModel
import java.text.FieldPosition

class VehicleFuelTypeFragment : Fragment() {


    private lateinit var viewModel: CreateProfileViewModel

    private var binding: VehicleFuelTypeFragmentBinding? = null

    private var listOfItems: List<FuelType> = enumValues<FuelType>().toList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = VehicleFuelTypeFragmentBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = requireActivity().getViewModel(CreateProfileViewModel::class.java)

        binding?.recyclerView?.recyclerView?.let {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = object : GenericRecyclerViewAdapter<FuelType>(listOfItems) {
                override fun getLayoutId(position: Int, obj: FuelType): Int =
                    R.layout.selectable_list_item

                override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder =
                    SelectableItemViewHolder<FuelType>(view, itemClickListener)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

    private val itemClickListener = object : BaseRecyclerViewHolder.OnItemClickListener {
        override fun onItemClicked(position: Int) {

            viewModel.newVehicleProfile.fuelType = listOfItems[position]

            Log.d(TAG, "onItemClicked: Vehicle Profile = ${viewModel.newVehicleProfile}")

            navigateToNextStep()
        }
    }


    private fun navigateToNextStep() {
        findNavController().navigate(
            VehicleFuelTypeFragmentDirections
                .actionVehicleFuelTypeFragmentToVehicleTransmissionFragment())
    }


    companion object {
        private val TAG = VehicleFuelTypeFragment::class.simpleName

        fun newInstance() = VehicleFuelTypeFragment()
    }

}