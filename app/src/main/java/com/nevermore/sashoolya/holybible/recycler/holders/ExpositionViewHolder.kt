package com.nevermore.sashoolya.holybible.recycler.holders

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.nevermore.sashoolya.holybible.R
import com.nevermore.sashoolya.holybible.data.pojo.Exposition
import com.nevermore.sashoolya.holybible.databinding.ItemSectionBinding
import com.nevermore.sashoolya.holybible.tools.dp

class ExpositionViewHolder(parent: ViewGroup) :
    BaseViewHolder<Exposition>(parent, R.layout.item_section) {

    override fun bindItem(item: Exposition) {
        super.bindItem(item)

        val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        val tvName = itemView.findViewById<TextView>(R.id.tvName)
        val photo = itemView.findViewById<ImageView>(R.id.photo)

        tvTitle.text = itemView.context.getString(R.string.exhibit, item.idPoint)
        tvName.text = item.name
        photo.layoutParams = LinearLayout.LayoutParams(100.dp, 100.dp)
        Glide.with(itemView.context).load(item.logo).into(photo)
    }
}