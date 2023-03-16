package com.nevermore.sashoolya.holybible.recycler.holders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.nevermore.sashoolya.holybible.R
import com.nevermore.sashoolya.holybible.data.pojo.Section
import com.nevermore.sashoolya.holybible.databinding.ItemSectionBinding

class SectionViewHolder(parent : ViewGroup) : BaseViewHolder<Section>(parent, R.layout.item_section){

    private val binding = ItemSectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    override fun bindItem(item: Section) {
        super.bindItem(item)
        itemView.run {
            binding.tvTitle.text = context.getString(R.string.section, item.id)
            binding.tvName.text = item.name
            Glide.with(context).load(item.logo).into(binding.photo)
        }
    }
}