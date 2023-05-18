package by.krokam.belarus_bible.recycler.holders

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import by.krokam.belarus_bible.R
import by.krokam.belarus_bible.data.pojo.Exposition
import by.krokam.belarus_bible.tools.dp

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