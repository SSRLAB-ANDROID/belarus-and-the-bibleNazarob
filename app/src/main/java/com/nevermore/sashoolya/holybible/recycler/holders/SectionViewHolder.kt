package com.nevermore.sashoolya.holybible.recycler.holders

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.nevermore.sashoolya.holybible.R
import com.nevermore.sashoolya.holybible.data.pojo.Section
import com.nevermore.sashoolya.holybible.databinding.ItemSectionBinding

class SectionViewHolder(parent: ViewGroup) :
    BaseViewHolder<Section>(parent, R.layout.item_section) {

    override fun bindItem(item: Section) {
        super.bindItem(item)

        val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        val tvName = itemView.findViewById<TextView>(R.id.tvName)
        val photo = itemView.findViewById<ImageView>(R.id.photo)

        tvTitle.text = itemView.context.getString(R.string.section, item.id)
        tvName.text = item.name
        Glide.with(itemView.context).load(item.logo).into(photo)
    }
}