package com.turbocare.vehicleprofiling.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewHolder<T>(
    itemView: View,
    itemClickListener: OnItemClickListener
) : RecyclerView.ViewHolder(itemView), GenericRecyclerViewAdapter.Binder<T> {

    init {
        itemView.setOnClickListener { itemClickListener.onItemClicked(adapterPosition) }
    }

    abstract override fun bind(data: T)

    interface OnItemClickListener {
        fun onItemClicked(position: Int)
    }
}