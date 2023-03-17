package com.nevermore.sashoolya.holybible.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.nevermore.sashoolya.holybible.R
import com.nevermore.sashoolya.holybible.data.pojo.Exposition
import com.nevermore.sashoolya.holybible.databinding.FragmentExponateBinding
import com.nevermore.sashoolya.holybible.tools.CustomPagerAdapter
import com.nevermore.sashoolya.holybible.tools.provider
import com.nevermore.sashoolya.holybible.vm.ExponateViewModel
import nl.changer.audiowife.AudioWife

class ExponateFragment : BaseFragment() {

    private lateinit var viewModel: ExponateViewModel
    private lateinit var mBinding: FragmentExponateBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[ExponateViewModel::class.java]

        return super.onCreateView(inflater, container, savedInstanceState)
    }

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
        var exponate = viewModel.getExponate()

        if (exponate == null) {
            exponate = provider.expos!!.find { it.lang == provider.langManager.langNumber }!!
            viewModel.setExponate(exponate)

            Glide.with(requireContext()).load(exponate.photo).into(mBinding.photo)
            mBinding.tvTitle.text = getString(R.string.exhibit, exponate.idPoint)
            mBinding.tvName.text = exponate.name
            mBinding.tvPlace.text = exponate.pointMuseum

            setupTabs(exponate.textLong, exponate.text)
            initPlayer(Uri.parse(exponate.sound))
        } else {
            exponate = viewModel.getExponate()

            Glide.with(requireContext()).load(exponate!!.photo).into(mBinding.photo)
            mBinding.tvTitle.text = getString(R.string.exhibit, exponate.idPoint)
            mBinding.tvName.text = exponate.name
            mBinding.tvPlace.text = exponate.pointMuseum

            setupTabs(exponate.textLong, exponate.text)
            initPlayer(Uri.parse(exponate.sound))
        }
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