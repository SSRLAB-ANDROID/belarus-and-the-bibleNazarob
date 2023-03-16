package com.nevermore.sashoolya.holybible.mvvm

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.nevermore.sashoolya.holybible.data.pojo.Section
import com.nevermore.sashoolya.holybible.recycler.SectionAdapter
import com.nevermore.sashoolya.holybible.tools.provider
import io.reactivex.schedulers.Schedulers

class SectionsFragment : BaseListFragment<Section>() {
    private var secs = listOf<Section>()
    override val adapter = SectionAdapter()
    private val liveSections = provider.appDao.getSectons()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        adapter.onClick = {
            provider.secs = secs.filter{ s -> s.id == it.id }
            provider.selectedSection = it
            provider.rootRouter.navigateTo(FragmentScreen{ExpositionsFragment()})
        }
    }

    override fun setupObservers() {
        liveSections.removeObservers(this)
        liveSections.observe(this, Observer { it ->
            secs = it!!
            startRefresh()
            adapter.items = it.filter { it.lang == provider.langManager.langNumber }
            stopRefresh()
        })
    }

    override fun onRefreshStarted() {
        subs.add(provider.apiService.getSections()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({
                    provider.appDao.insertSectons(it!!)
                }, {
                    stopRefresh()
                })
        )
    }
}