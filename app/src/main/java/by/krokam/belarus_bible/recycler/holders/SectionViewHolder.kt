package by.krokam.belarus_bible.recycler.holders

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import by.krokam.belarus_bible.R
import by.krokam.belarus_bible.data.pojo.Section

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