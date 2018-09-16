package com.nevermore.sashoolya.holybible.mvvm

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nevermore.sashoolya.holybible.R


abstract class BaseFragment : Fragment(){
    private lateinit var swipe : SwipeRefreshLayout

    abstract fun getContentView(inflater: LayoutInflater, container: ViewGroup?) : View?

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return SwipeRefreshLayout(context!!).apply {
            id = R.id.swipe
            addView(getContentView(inflater, container))
            swipe = this
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipe.setOnRefreshListener {onRefreshStarted()}
    }

    fun startRefresh(){
        swipe.isRefreshing = true
        onRefreshStarted()
    }

    abstract fun onRefreshStarted()

    fun stopRefresh(){
        swipe.isRefreshing = false
    }
}