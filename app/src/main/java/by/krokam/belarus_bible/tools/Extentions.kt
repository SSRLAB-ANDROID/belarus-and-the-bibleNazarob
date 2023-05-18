package by.krokam.belarus_bible.tools

import android.content.res.Resources
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.krokam.belarus_bible.app.Application

val provider = Application.INSTANCE!!.provider

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun View.isVisibleOrGone(value : Boolean){
    this.visibility = if(value) View.VISIBLE else View.GONE
}

fun RecyclerView.setVertical(){
    this.layoutManager = LinearLayoutManager(
        context,
        RecyclerView.VERTICAL,
        false
    )
}
