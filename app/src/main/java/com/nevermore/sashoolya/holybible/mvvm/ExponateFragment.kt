package com.nevermore.sashoolya.holybible.mvvm

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.nevermore.sashoolya.holybible.R
import com.nevermore.sashoolya.holybible.databinding.FragmentExponateBinding
import com.nevermore.sashoolya.holybible.tools.CustomPagerAdapter
import com.nevermore.sashoolya.holybible.tools.provider
import nl.changer.audiowife.AudioWife

class ExponateFragment : BaseFragment() {

    private lateinit var mBinding: FragmentExponateBinding
    override fun getContentView(inflater: LayoutInflater, container: ViewGroup?): View? {
        mBinding = FragmentExponateBinding.inflate(layoutInflater)

        return mBinding.root
    }

    override fun onRefreshStarted() {
        AudioWife.getInstance().release()
        setupObservers()
        stopRefresh()
    }


    override fun setupObservers() {
        val exponate = provider.expos!!.find { it.lang == provider.langManager.langNumber }!!
        //text.text = exponate.text
        Glide.with(requireContext()).load(exponate.photo).into(mBinding.photo)
        mBinding.tvTitle.text = getString(R.string.exhibit, exponate.idPoint)
        mBinding.tvName.text = exponate.name
        mBinding.tvPlace.text = exponate.pointMuseum

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
        mBinding.tabs.setupWithViewPager(mBinding.pager.apply { this.adapter = adapter })
    }

    private fun initPlayer(uri: Uri) {
        AudioWife.getInstance().release()
        AudioWife.getInstance()
                .init(requireContext(), uri)
                .setPlayView(mBinding.icPlay)
                .setPauseView(mBinding.icStop)
                .setSeekBar(mBinding.seekBar)
                .setRuntimeView(mBinding.tvCurTime)


                .setTotalTimeView(mBinding.tvAllTime)
        mBinding.icPlay.visibility = View.VISIBLE
        mBinding.icStop.visibility = View.INVISIBLE
    }

    override fun onStop() {
        super.onStop()
        AudioWife.getInstance().release()
    }

}