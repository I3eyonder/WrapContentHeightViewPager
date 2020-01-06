package com.hieupt.view.viewpager

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.viewpager.widget.ViewPager
import kotlin.math.max

/**
 * Created by HieuPT on 1/4/2020.
 */
class WrapContentHeightViewPager : ViewPager {
    private var currentView: View? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var newHeightMeasureSpec = heightMeasureSpec
        currentView?.let { currentView ->
            currentView.measure(
                widthMeasureSpec,
                MeasureSpec.makeMeasureSpec(heightMeasureSpec, MeasureSpec.AT_MOST)
            )
            val height = max(currentView.measuredHeight, minimumHeight)
            val newHeight = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
            newHeightMeasureSpec = newHeight
        }
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec)
    }

    /**
     * This method should be called when the ViewPager changes to another page. For best results
     * call this method in the adapter's setPrimary
     *
     * @param currentView PagerAdapter item view
     */
    fun onPageChanged(currentView: View?) {
        this.currentView = currentView
        requestLayout()
    }
}