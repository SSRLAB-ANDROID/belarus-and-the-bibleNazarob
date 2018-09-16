package com.nevermore.sashoolya.holybible.mvvm

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.nevermore.sashoolya.holybible.R
import com.nevermore.sashoolya.holybible.util.provider
import kotlinx.android.synthetic.main.fragment_exponate.*
import nl.changer.audiowife.AudioWife

class ExponateFragment : BaseFragment(){
    override fun getContentView(inflater: LayoutInflater, container: ViewGroup?): View? {
        return inflater.inflate(R.layout.fragment_exponate, container, false)
    }

    override fun onRefreshStarted() {
        AudioWife.getInstance().release()
        setupView()
        stopRefresh()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       setupView()
    }

    private fun setupView(){
        val exponate = provider.selectedExposition!!
        text.text = exponate.text
        Glide.with(context!!).load(exponate.logo).into(photo)
        tvTitle.text = getString(R.string.exhibit, exponate.idPoint)
        tvName.text = exponate.name
        tvPlace.text = exponate.pointMuseum
        initPlayer(Uri.parse(exponate.sound))
    }

    private fun initPlayer(uri : Uri){
        AudioWife.getInstance()
                .init(context!!, uri)
                .setPlayView(icPlay)
                .setPauseView(icStop)
                .setSeekBar(seekBar)
    }

    override fun onDestroy() {
        super.onDestroy()
        AudioWife.getInstance().release()
    }
}