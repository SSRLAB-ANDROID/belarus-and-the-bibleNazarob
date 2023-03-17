package com.nevermore.sashoolya.holybible.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nevermore.sashoolya.holybible.R
import com.nevermore.sashoolya.holybible.navigation.RootActivity
import com.nevermore.sashoolya.holybible.vm.ExponateViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment : Fragment(){

    private val isInited = MutableLiveData<Boolean>().apply { value = false }
    val subs = CompositeDisposable()
    private lateinit var swipe : SwipeRefreshLayout

    abstract fun getContentView(inflater: LayoutInflater, container: ViewGroup?) : View?

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return SwipeRefreshLayout(requireContext()).apply {
            id = R.id.swipe
            addView(getContentView(inflater, container))
            swipe = this
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        swipe.setOnRefreshListener {refreshData()}
        notifyStateChanged()
        setupOnInit()
    }
    fun notifyStateChanged(){
        (activity as RootActivity).adaptState(this)
    }

    private fun setupOnInit(){
        isInited.observe(viewLifecycleOwner, Observer {
            if(!it!!){
                refreshData()
                isInited.value = true
            }
        })
    }

    abstract fun setupObservers()

    fun startRefresh(){
        swipe.isRefreshing = true
    }

    fun refreshData(){
        startRefresh()
        onRefreshStarted()
    }

    abstract fun onRefreshStarted()

    fun stopRefresh(){
        swipe.isRefreshing = false
    }

    override fun onDestroy() {
        super.onDestroy()
        subs.dispose()
    }
}