package com.nevermore.sashoolya.holybible.recycler.holders

import android.view.ViewGroup
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.nevermore.sashoolya.holybible.R
import com.nevermore.sashoolya.holybible.data.pojo.Exposition
import com.nevermore.sashoolya.holybible.util.dp
import kotlinx.android.synthetic.main.item_section.view.*

class ExpositionViewHolder(parent : ViewGroup) : BaseViewHolder<Exposition>(parent, R.layout.item_section){

    override fun bindItem(item: Exposition) {
        super.bindItem(item)
        itemView.run {
            tvTitle.text = context.getString(R.string.exhibit,item.id)
            tvName.text = item.name
            photo.layoutParams = LinearLayout.LayoutParams(100.dp, 100.dp)
            Glide.with(context).load(item.logo).into(photo)
        }
    }
}