package com.nevermore.sashoolya.holybible.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.nevermore.sashoolya.holybible.R
import com.nevermore.sashoolya.holybible.databinding.FragmentExponateBinding
import com.nevermore.sashoolya.holybible.tools.CustomPagerAdapter
import com.nevermore.sashoolya.holybible.tools.provider
import com.nevermore.sashoolya.holybible.vm.ExponateViewModel

class ExponateFragment : BaseFragment() {

    private lateinit var viewModel: ExponateViewModel
    private lateinit var mBinding: FragmentExponateBinding
    private var textFragment1: TextFragment? = null
    private var textFragment2: TextFragment? = null
    private var customPagerAdapter: CustomPagerAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[ExponateViewModel::class.java]

        mBinding = if (viewModel.checkBindingSaved()) {
            FragmentExponateBinding.inflate(layoutInflater)
        } else {
            viewModel.getBinding()!!

        }

        swipe = mBinding.swipeRefreshExponate

        return mBinding.root
    }

    override fun getContentView(inflater: LayoutInflater, container: ViewGroup?): View {
        return mBinding.root
    }

    override fun onRefreshStarted() {
        if (resources.configuration.orientation == viewModel.getConfig()) {
            println("update")

            viewModel.mpRefresh()
            setupObservers()
            stopRefresh()
        } else {
            println("change")
            viewModel.saveConfig(resources.configuration.orientation)

            setupObservers()
            stopRefresh()
        }


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
            viewModel.initializeMediaPlayer(Uri.parse(exponate.sound), mBinding, requireContext())

            mBinding.apPlayButton.setOnClickListener {
                viewModel.playAudio(mBinding)
            }
        } else {
            exponate = viewModel.getExponate()

            Glide.with(requireContext()).load(exponate!!.photo).into(mBinding.photo)
            mBinding.tvTitle.text = getString(R.string.exhibit, exponate.idPoint)
            mBinding.tvName.text = exponate.name
            mBinding.tvPlace.text = exponate.pointMuseum

            setupTabs(exponate.textLong, exponate.text)
            viewModel.initializeMediaPlayer(Uri.parse(exponate.sound), mBinding, requireContext())

            mBinding.apPlayButton.setOnClickListener {
                viewModel.playAudio(mBinding)
            }
        }
    }

    private fun setupTabs(textLong: String, textShort: String) {

        textFragment1 = TextFragment().apply {
            text.value = textShort
        }
        textFragment2 = TextFragment().apply {
            text.value = textLong
        }

        customPagerAdapter = CustomPagerAdapter(childFragmentManager).apply {
            addFragment(textFragment1!!, resources.getString(R.string.short_desc))

            if (textLong.isNotEmpty() && textLong != textShort) {
                addFragment(textFragment2!!, resources.getString(R.string.long_desc))
            }
        }

        mBinding.tabs.setupWithViewPager(mBinding.pager.apply { this.adapter = customPagerAdapter })
        customPagerAdapter!!.notifyDataSetChanged()
    }

    override fun onStop() {
        super.onStop()
        viewModel.saveBinding(mBinding)
    }
}