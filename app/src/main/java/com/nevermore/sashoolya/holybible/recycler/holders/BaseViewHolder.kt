package com.nevermore.sashoolya.holybible.recycler.holders

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

abstract class BaseViewHolder<T>(parent: ViewGroup, idLayout: Int) :
        RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(idLayout, parent, false)) {
    open fun bindItem(item: T) {}
}