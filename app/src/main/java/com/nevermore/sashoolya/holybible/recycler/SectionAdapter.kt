package com.nevermore.sashoolya.holybible.recycler

import android.view.ViewGroup
import com.nevermore.sashoolya.holybible.data.pojo.Section
import com.nevermore.sashoolya.holybible.recycler.holders.BaseViewHolder
import com.nevermore.sashoolya.holybible.recycler.holders.SectionViewHolder

class SectionAdapter : BaseAdapter<Section>(){
    override fun getItemView(parent: ViewGroup): BaseViewHolder<Section> {
        return SectionViewHolder(parent)
    }

}