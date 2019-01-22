package com.nevermore.sashoolya.holybible.tools

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.View


class CustomPager : ViewPager {

    private var mCurrentView: View? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    public override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (mCurrentView == null) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            return
        }
        var height = 0
        mCurrentView!!.measure(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
        val h = mCurrentView!!.measuredHeight
        if (h > height) height = h
        val hms = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY)

        super.onMeasure(widthMeasureSpec, hms)
    }

    fun measureCurrentView(currentView: View) {
        mCurrentView = currentView
        requestLayout()
    }

    fun measureFragment(view: View?): Int {
        if (view == null)
            return 0

        view.measure(0, 0)
        return view.measuredHeight
    }
}