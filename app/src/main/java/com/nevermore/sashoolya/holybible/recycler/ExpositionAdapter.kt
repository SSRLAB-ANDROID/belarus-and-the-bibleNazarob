package com.nevermore.sashoolya.holybible.recycler

import android.view.ViewGroup
import com.nevermore.sashoolya.holybible.R
import com.nevermore.sashoolya.holybible.data.pojo.Exposition
import com.nevermore.sashoolya.holybible.data.pojo.Section
import com.nevermore.sashoolya.holybible.recycler.holders.BaseViewHolder
import com.nevermore.sashoolya.holybible.recycler.holders.ExpositionViewHolder
import com.nevermore.sashoolya.holybible.tools.provider
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
                    tvTitle.text = context.getString(R.string.section, provider.secs!!.find { it.lang == provider.langManager.langNumber}!!.id)
                    tvName.text =  provider.secs!!.find { it.lang == provider.langManager.langNumber}!!.name
                }
            }
        } else getItemView(parent)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Exposition>, position: Int) {
        if(position > 0) {
            super.onBindViewHolder(holder, position - 1)
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }

}