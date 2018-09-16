package com.nevermore.sashoolya.holybible.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nevermore.sashoolya.holybible.R
import com.nevermore.sashoolya.holybible.data.pojo.Exposition
import com.nevermore.sashoolya.holybible.data.pojo.Section
import com.nevermore.sashoolya.holybible.recycler.holders.BaseViewHolder
import com.nevermore.sashoolya.holybible.recycler.holders.ExpositionViewHolder
import kotlinx.android.synthetic.main.panel_title.view.*

class ExpositionAdapter(val section : Section) : BaseAdapter<Exposition>(){

    override fun getItemViewType(position: Int): Int {
        return if(position == 0) 0 else 1
    }

    override fun getItemView(parent: ViewGroup): BaseViewHolder<Exposition> {
        return ExpositionViewHolder(parent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Exposition> {
        return if (viewType == 0) {
            object : BaseViewHolder<Exposition>(parent, R.layout.panel_title) {}.apply {
                itemView.run {
                    tvTitle.text = context.getString(R.string.section, section.idLocale)
                    tvName.text = section.name
                }
            }
        } else getItemView(parent)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Exposition>, position: Int) {
        if(position > 0) {
            val item = items[position - 1]
            holder.bindItem(item)
            holder.itemView.setOnClickListener { onClick?.invoke(item) }
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }

}