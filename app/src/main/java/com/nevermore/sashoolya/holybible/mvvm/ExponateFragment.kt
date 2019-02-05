package com.nevermore.sashoolya.holybible.mvvm

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.nevermore.sashoolya.holybible.R
import com.nevermore.sashoolya.holybible.tools.CustomPagerAdapter
import com.nevermore.sashoolya.holybible.tools.provider
import kotlinx.android.synthetic.main.fragment_exponate.*
import nl.changer.audiowife.AudioWife

class ExponateFragment : BaseFragment() {
    override fun getContentView(inflater: LayoutInflater, container: ViewGroup?): View? {
        return inflater.inflate(R.layout.fragment_exponate, container, false)
    }

    override fun onRefreshStarted() {
        AudioWife.getInstance().release()
        setupObservers()
        stopRefresh()
    }


    override fun setupObservers() {
        val exponate = provider.expos!!.find { it.lang == provider.langManager.langNumber }!!
        //text.text = exponate.text
        Glide.with(context!!).load(exponate.photo).into(photo)
        tvTitle.text = getString(R.string.exhibit, exponate.idPoint)
        tvName.text = exponate.name
        tvPlace.text = exponate.pointMuseum

        setupTabs(exponate.textLong, exponate.text)
        initPlayer(Uri.parse(exponate.sound))
    }

    private fun setupTabs(textLong: String, textShort: String) {
        val adapter = CustomPagerAdapter(childFragmentManager).apply {
            addFragment(TextFragment().apply {
                text.value = textShort
            }, resources.getString(R.string.short_desc))

            if (textLong.isNotEmpty() && textLong != textShort) {
                addFragment(TextFragment().apply {
                    text.value = textLong
                }, resources.getString(R.string.long_desc))
            }
        }
        tabs.setupWithViewPager(pager.apply { this.adapter = adapter })
    }

    private fun initPlayer(uri: Uri) {
        AudioWife.getInstance().release()
        AudioWife.getInstance()
                .init(context!!, uri)
                .setPlayView(icPlay)
                .setPauseView(icStop)
                .setSeekBar(seekBar)
                .setRuntimeView(tvCurTime)


                .setTotalTimeView(tvAllTime)
        icPlay.visibility = View.VISIBLE
        icStop.visibility = View.INVISIBLE
    }

    override fun onStop() {
        super.onStop()
        AudioWife.getInstance().release()
    }

}