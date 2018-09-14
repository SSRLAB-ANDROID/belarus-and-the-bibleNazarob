package com.nevermore.sashoolya.holybible.recycler.holders

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.nevermore.sashoolya.holybible.R
import com.nevermore.sashoolya.holybible.data.pojo.Section
import kotlinx.android.synthetic.main.item_section.view.*

class SectionViewHolder(parent : ViewGroup) : BaseViewHolder<Section>(parent, R.layout.item_section){

    override fun bindItem(item: Section) {
        super.bindItem(item)
        itemView.run {
            tvTitle.text = context.getString(R.string.exhibit,item.id)
            tvName.text = item.name
            Glide.with(context).load(item.logo).into(photo)
        }
    }
}