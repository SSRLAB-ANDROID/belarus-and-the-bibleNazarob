package com.nevermore.sashoolya.holybible.mvvm

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.View
import com.nevermore.sashoolya.holybible.data.pojo.Exposition
import com.nevermore.sashoolya.holybible.navigation.RootScreens
import com.nevermore.sashoolya.holybible.recycler.ExpositionAdapter
import com.nevermore.sashoolya.holybible.util.getNavigation
import com.nevermore.sashoolya.holybible.util.provider
import io.reactivex.schedulers.Schedulers

class ExpositionsFragment : BaseListFragment<Exposition>(){
    override val adapter = ExpositionAdapter(provider.selectedSection!!).apply {
        onClick = {
            provider.selectedExposition = it
            getNavigation().navigateTo(RootScreens.EXPONATE_SCREEN)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        provider.appDao.getExpositions().observe(this, Observer {
            if(it!!.isEmpty()){
                startRefresh()
            }else{
                adapter.items = it
                stopRefresh()
            }
        })
    }

    override fun onRefreshStarted() {
        provider.apiService.getExpositions()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({
                    provider.appDao.insertExpositions(it!!.apply {
                        forEach { it.image = it.images[0] }
                    })
                },{
                    it.printStackTrace()
                })
    }
}