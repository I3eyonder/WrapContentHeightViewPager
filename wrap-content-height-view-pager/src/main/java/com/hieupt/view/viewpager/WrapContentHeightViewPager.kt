package com.hieupt.view.viewpager

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.use
import androidx.viewpager.widget.ViewPager
import com.hieupt.wrapcontentheightviewpager.R
import kotlin.math.max

/**
 * Created by HieuPT on 1/4/2020.
 */
class WrapContentHeightViewPager : ViewPager {

    enum class MeasureMode {
        AT_MOST,
        FIT_CHILDREN
    }

    private var currentView: View? = null

    private var measureMode: MeasureMode = MeasureMode.AT_MOST

    constructor(context: Context, measureMode: MeasureMode = MeasureMode.AT_MOST) : super(context) {
        init(context)
        this.measureMode = measureMode
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet? = null) {
        context.theme.obtainStyledAttributes(attrs, R.styleable.WrapContentHeightViewPager, 0, 0)
            .use { ta ->
                measureMode =
                    when (ta.getInt(R.styleable.WrapContentHeightViewPager_measureMode, 0)) {
                        0 -> MeasureMode.AT_MOST
                        else -> MeasureMode.FIT_CHILDREN
                    }
            }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (measureMode == MeasureMode.FIT_CHILDREN) {
            onMeasureFitChildren(widthMeasureSpec, heightMeasureSpec)
        } else {
            onMeasureAtMost(widthMeasureSpec, heightMeasureSpec)
        }
    }

    @SuppressLint("WrongCall")
    private fun onMeasureAtMost(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var newHeightMeasureSpec = heightMeasureSpec
        currentView?.let { currentView ->
            currentView.measure(widthMeasureSpec, heightMeasureSpec)
            val height = max(currentView.measuredHeight, minimumHeight)
            val newHeight = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
            newHeightMeasureSpec = newHeight
        }
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec)
    }

    @SuppressLint("WrongCall")
    private fun onMeasureFitChildren(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var newHeightMeasureSpec = heightMeasureSpec
        currentView?.let { currentView ->
            currentView.measure(
                widthMeasureSpec,
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
            )
            val height = max(currentView.measuredHeight, minimumHeight)
            val newHeight = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
            if (layoutParams.height != 0 && newHeightMeasureSpec != newHeight) {
                post {
                    layoutParams.height = height
                    requestLayout()
                }
            } else {
                newHeightMeasureSpec = newHeight
            }
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