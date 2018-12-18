package com.nevermore.sashoolya.holybible.mvvm

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nevermore.sashoolya.holybible.R
import kotlinx.android.synthetic.main.fragment_text.view.*

class TextFragment : Fragment(){
    val text  = MutableLiveData<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_text, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        text.observe(this, Observer {
            view.text.text = it!!
        })
    }
}