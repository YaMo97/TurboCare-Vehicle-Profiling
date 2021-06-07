package com.turbocare.vehicleprofiling.ui.common

import android.view.View
import com.turbocare.vehicleprofiling.databinding.SelectableListItemBinding
import com.turbocare.vehicleprofiling.ui.base.BaseRecyclerViewHolder

class SelectableItemViewHolder<T>(
    itemView: View,
    onItemClickListener: OnItemClickListener
) : BaseRecyclerViewHolder<T>(itemView, onItemClickListener) {

    private val binding = SelectableListItemBinding.bind(itemView)

    override fun bind(data: T) {
        binding.option.text = data.toString()
    }
}