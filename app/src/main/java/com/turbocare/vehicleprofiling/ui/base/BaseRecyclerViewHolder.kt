package com.turbocare.vehicleprofiling.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewHolder<T>(itemView: View)
    : RecyclerView.ViewHolder(itemView), GenericRecyclerViewAdapter.Binder<T> {

    abstract override fun bind(data: T)
}