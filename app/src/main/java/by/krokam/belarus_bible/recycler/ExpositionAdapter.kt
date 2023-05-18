package by.krokam.belarus_bible.recycler

import android.view.ViewGroup
import android.widget.TextView
import by.krokam.belarus_bible.R
import by.krokam.belarus_bible.data.pojo.Exposition
import by.krokam.belarus_bible.data.pojo.Section
import by.krokam.belarus_bible.recycler.holders.BaseViewHolder
import by.krokam.belarus_bible.recycler.holders.ExpositionViewHolder
import by.krokam.belarus_bible.tools.provider

class ExpositionAdapter(val section: Section) : BaseAdapter<Exposition>() {

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) 0 else 1
    }

    override fun getItemView(parent: ViewGroup): BaseViewHolder<Exposition> {
        return ExpositionViewHolder(parent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Exposition> {

        return if (viewType == 0) {
            object : BaseViewHolder<Exposition>(parent, R.layout.panel_title) {}.apply {

                val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
                val tvName = itemView.findViewById<TextView>(R.id.tvName)

                tvTitle.text = itemView.context.getString(R.string.section, provider.secs!!.find { it.lang == provider.langManager.langNumber }!!.id)
                tvName.text = provider.secs!!.find { it.lang == provider.langManager.langNumber }!!.name
            }
        } else getItemView(parent)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Exposition>, position: Int) {
        if (position > 0) {
            super.onBindViewHolder(holder, position - 1)
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }

}