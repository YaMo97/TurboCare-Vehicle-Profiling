package com.turbocare.vehicleprofiling.ui.common

import android.view.View
import com.turbocare.vehicleprofiling.databinding.SelectableListItemBinding
import com.turbocare.vehicleprofiling.ui.base.BaseRecyclerViewHolder

class SelectableItemViewHolder(
    itemView: View,
    onItemClickListener: OnItemClickListener
) : BaseRecyclerViewHolder<String>(itemView, onItemClickListener) {

    private val binding = SelectableListItemBinding.bind(itemView)

    override fun bind(data: String) {
        binding.option.text = data
    }
}