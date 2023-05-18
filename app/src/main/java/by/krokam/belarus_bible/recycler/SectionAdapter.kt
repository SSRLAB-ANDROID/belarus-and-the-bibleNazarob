package by.krokam.belarus_bible.recycler

import android.view.ViewGroup
import by.krokam.belarus_bible.data.pojo.Section
import by.krokam.belarus_bible.recycler.holders.BaseViewHolder
import by.krokam.belarus_bible.recycler.holders.SectionViewHolder

class SectionAdapter : BaseAdapter<Section>(){
    override var items = listOf<Section>()
        set(value) {
            field = value.sortedBy { it.id.toInt() }
            notifyDataSetChanged()
        }
    override fun getItemView(parent: ViewGroup): BaseViewHolder<Section> {
        return SectionViewHolder(parent)
    }

}