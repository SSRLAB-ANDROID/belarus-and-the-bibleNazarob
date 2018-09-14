package com.nevermore.sashoolya.holybible.mvvm

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nevermore.sashoolya.holybible.R
import com.nevermore.sashoolya.holybible.recycler.SectionAdapter
import com.nevermore.sashoolya.holybible.util.provider
import com.nevermore.sashoolya.holybible.util.setVertical
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_recycler.*

class SectionsFragment : Fragment(){
    val adapter = SectionAdapter()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return RecyclerView(context!!).apply { id = R.id.recycler }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler.run {
            setVertical()
            adapter = this@SectionsFragment.adapter
        }

        provider.apiService.getSections()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({
            provider.appDao.insertSectons(it!!)
        },{
            it.printStackTrace()
        })

        provider.appDao.getSectons().observe(this, Observer {
            adapter.items = it!!
        })
    }

}