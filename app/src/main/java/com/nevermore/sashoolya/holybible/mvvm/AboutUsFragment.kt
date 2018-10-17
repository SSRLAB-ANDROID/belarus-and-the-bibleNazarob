package com.nevermore.sashoolya.holybible.mvvm

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nevermore.sashoolya.holybible.R
import kotlinx.android.synthetic.main.fragment_about.view.*

class AboutUsFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_about, container, false).apply {
            text.text = Html.fromHtml(resources.getString(R.string.about_us_text))
        }
    }
}