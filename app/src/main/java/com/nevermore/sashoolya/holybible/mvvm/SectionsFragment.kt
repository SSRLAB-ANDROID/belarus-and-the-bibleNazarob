package com.nevermore.sashoolya.holybible.mvvm

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.View
import com.nevermore.sashoolya.holybible.data.pojo.Section
import com.nevermore.sashoolya.holybible.navigation.RootScreens
import com.nevermore.sashoolya.holybible.recycler.SectionAdapter
import com.nevermore.sashoolya.holybible.util.getNavigation
import com.nevermore.sashoolya.holybible.util.provider
import io.reactivex.schedulers.Schedulers

class SectionsFragment : BaseListFragment<Section>(){
    override val adapter = SectionAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        provider.appDao.getSectons().observe(this, Observer {
            if(it!!.isEmpty()){
                startRefresh()
            }else{
                adapter.items = it.filter { it.lang == "1" }
                stopRefresh()
            }
        })

        adapter.onClick = {
            provider.selectedSection = it
            getNavigation().navigateTo(RootScreens.EXPOSITIONS_SCREEN)
        }
    }

    override fun onRefreshStarted() {
        provider.apiService.getSections()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({
                    provider.appDao.insertSectons(it!!)
                },{
                    it.printStackTrace()
                })
    }
}