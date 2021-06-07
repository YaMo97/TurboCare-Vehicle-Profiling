package com.turbocare.vehicleprofiling.ui.profile.list

import android.view.View
import android.widget.TextView
import com.turbocare.vehicleprofiling.R
import com.turbocare.vehicleprofiling.data.model.VehicleProfile
import com.turbocare.vehicleprofiling.databinding.VehicleListItemBinding
import com.turbocare.vehicleprofiling.ui.base.BaseRecyclerViewHolder

class VehicleListViewHolder(itemView: View, onItemClickListener: OnItemClickListener)
    : BaseRecyclerViewHolder<VehicleProfile>(itemView, onItemClickListener) {

    private val binding = VehicleListItemBinding.bind(itemView)

    override fun bind(data: VehicleProfile) {
        binding.registrationNumber.text = data.registrationNumber
        binding.vehicleName.text = data.getVehicleName()
    }
}