package com.nevermore.sashoolya.holybible.mvvm

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.View
import com.nevermore.sashoolya.holybible.data.pojo.Exposition
import com.nevermore.sashoolya.holybible.navigation.RootScreens
import com.nevermore.sashoolya.holybible.recycler.ExpositionAdapter
import com.nevermore.sashoolya.holybible.util.provider
import io.reactivex.schedulers.Schedulers

class ExpositionsFragment : BaseListFragment<Exposition>() {
    private var exps = listOf<Exposition>()

    private  var liveExpositions  = provider.appDao.getExpositions(provider.secs!!.find {
        it.lang == provider.langManager.langNumber
    }!!.id)
    override val adapter = ExpositionAdapter(provider.selectedSection!!).apply {
        onClick = {
            provider.selectedExposition = it
            provider.expos = exps.filter {e -> e.idPoint == it.idPoint }
            provider.rootRouter.navigateTo(RootScreens.EXPONATE_SCREEN)
        }
    }

    override fun setupObservers() {
        liveExpositions.removeObservers(this)
        liveExpositions.observe(this, Observer {
            startRefresh()
            adapter.items = it!!.filter { it.lang == provider.langManager.langNumber }
            exps = it
            stopRefresh()
        })

    }

    override fun onRefreshStarted() {
        subs.add(provider.apiService.getExpositions()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({
                    it!!
                    provider.appDao.insertExpositions(it!!)
                }, {
                    stopRefresh()
                })
        )
    }
}