package com.nevermore.sashoolya.holybible.tools

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class CustomPagerAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {
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

    override fun getPageTitle(pos: Int): CharSequence {
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

//    override fun getItemPosition(`object`: Any): Int {
//        return PagerAdapter.POSITION_NONE
//    }
}