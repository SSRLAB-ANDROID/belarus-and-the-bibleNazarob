package com.nevermore.sashoolya.holybible.recycler

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.nevermore.sashoolya.holybible.recycler.holders.BaseViewHolder
import com.nevermore.sashoolya.holybible.tools.dp

abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>() {
    var onClick: ((item : T) -> Unit)? = null
    open var items: List<T> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    abstract fun getItemView(parent: ViewGroup) : BaseViewHolder<T>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return getItemView(parent)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
            val item = items[position]
            holder.bindItem(item)
            holder.itemView.setOnClickListener { onClick?.invoke(item) }
        holder.itemView.run{
            setPadding(8.dp, 2.dp, 8.dp, 4.dp)
            if (position == 0) {
                setPadding(8.dp, 12.dp, 8.dp, 4.dp)
            }
            if(position == items.size - 1){
                setPadding(8.dp,2.dp, 8.dp, 12.dp)
            }
        }
    }

    override fun getItemCount(): Int { return items.size}
}