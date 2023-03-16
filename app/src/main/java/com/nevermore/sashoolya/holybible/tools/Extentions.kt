package com.nevermore.sashoolya.holybible.tools

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.nevermore.sashoolya.holybible.app.Application

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


//fun loadCircleImage(context : Context, url : String, imageView : ImageView){
//    Glide.with(context).load(url)
//        .asBitmap().centerCrop().into(object : BitmapImageViewTarget(imageView) {
//                override fun setResource(resource: Bitmap) {
//                    val circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.resources, resource)
//                    circularBitmapDrawable.isCircular = true
//                    imageView.setImageDrawable(circularBitmapDrawable)
//                }
//            })
//}
