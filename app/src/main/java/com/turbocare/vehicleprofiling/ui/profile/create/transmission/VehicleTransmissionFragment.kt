package com.turbocare.vehicleprofiling.ui.profile.create.transmission

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.turbocare.vehicleprofiling.R
import com.turbocare.vehicleprofiling.data.model.Transmission
import com.turbocare.vehicleprofiling.databinding.VehicleTransmissionFragmentBinding
import com.turbocare.vehicleprofiling.ui.base.BaseRecyclerViewHolder
import com.turbocare.vehicleprofiling.ui.base.GenericRecyclerViewAdapter
import com.turbocare.vehicleprofiling.ui.common.SelectableItemViewHolder
import com.turbocare.vehicleprofiling.ui.profile.create.CreateProfileViewModel
import com.turbocare.vehicleprofiling.util.getViewModel

class VehicleTransmissionFragment : Fragment() {


    private lateinit var viewModel: CreateProfileViewModel

    private var binding: VehicleTransmissionFragmentBinding? = null

    private var listOfItems: List<Transmission> = enumValues<Transmission>().toList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = VehicleTransmissionFragmentBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = requireActivity().getViewModel(CreateProfileViewModel::class.java)

        binding?.recyclerView?.recyclerView?.let {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = object : GenericRecyclerViewAdapter<Transmission>(listOfItems) {
                override fun getLayoutId(position: Int, obj: Transmission): Int =
                    R.layout.selectable_list_item

                override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder =
                    SelectableItemViewHolder<Transmission>(view, itemClickListener)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

    private val itemClickListener = object : BaseRecyclerViewHolder.OnItemClickListener {
        override fun onItemClicked(position: Int) {

            viewModel.newVehicleProfile.transmission = listOfItems[position]

            Log.d(TAG, "onItemClicked: Vehicle Profile = ${viewModel.newVehicleProfile}")

            saveVehicleProfile()
        }
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


    companion object {
        private val TAG = VehicleTransmissionFragment::class.simpleName

        fun newInstance() = VehicleTransmissionFragment()
    }
}