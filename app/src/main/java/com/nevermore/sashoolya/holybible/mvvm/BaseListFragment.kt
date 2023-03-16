package com.nevermore.sashoolya.holybible.mvvm

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nevermore.sashoolya.holybible.R

import com.nevermore.sashoolya.holybible.recycler.BaseAdapter
import com.nevermore.sashoolya.holybible.tools.setVertical

abstract class BaseListFragment<T> : BaseFragment(){

    abstract val adapter : BaseAdapter<T>

    override fun getContentView(inflater: LayoutInflater, container: ViewGroup?) : View?{
        return RecyclerView(requireContext()).apply {
            id = R.id.recycler
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.recycler).run {
            setVertical()
            adapter = this@BaseListFragment.adapter
        }
    }

}