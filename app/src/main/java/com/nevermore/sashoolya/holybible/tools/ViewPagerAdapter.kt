package com.nevermore.sashoolya.holybible.tools

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ViewPagerAdapter(val fm : FragmentManager) : FragmentPagerAdapter(fm) {
    private val mFragmentList = mutableListOf<Fragment>()
    private val mFragmentTitleList = mutableListOf<String>()

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
}