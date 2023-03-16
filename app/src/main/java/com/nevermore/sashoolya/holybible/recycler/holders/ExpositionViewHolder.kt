package com.nevermore.sashoolya.holybible.recycler.holders

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.nevermore.sashoolya.holybible.R
import com.nevermore.sashoolya.holybible.data.pojo.Exposition
import com.nevermore.sashoolya.holybible.databinding.FragmentExponateBinding
import com.nevermore.sashoolya.holybible.databinding.ItemSectionBinding
import com.nevermore.sashoolya.holybible.tools.dp

class ExpositionViewHolder(parent : ViewGroup) : BaseViewHolder<Exposition>(parent, R.layout.item_section){

    private val binding = FragmentExponateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    override fun bindItem(item: Exposition) {
        super.bindItem(item)
        itemView.run {
            binding.tvTitle.text = context.getString(R.string.exhibit,item.idPoint)
            binding.tvName.text = item.name
            binding.photo.layoutParams = LinearLayout.LayoutParams(100.dp, 100.dp)
            Glide.with(context).load(item.logo).into(binding.photo)
        }
    }
}