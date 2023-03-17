package com.nevermore.sashoolya.holybible.tools

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import android.view.ViewGroup



class CustomPagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {
    private val mFragmentList = mutableListOf<Fragment>()
    private val mFragmentTitleList = mutableListOf<String>()
    private var mCurrentPosition = -1

    override fun getItem(pos: Int): Fragment {
        return mFragmentList[pos]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

    override fun getPageTitle(pos: Int): CharSequence? {
        return mFragmentTitleList[pos]
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, item: Any) {
        super.setPrimaryItem(container, position, item)
        if (position != mCurrentPosition) {
            (item as Fragment).view?.let {
                mCurrentPosition = position
                (container as CustomPager).measureCurrentView(it)
            }
        }
    }
}