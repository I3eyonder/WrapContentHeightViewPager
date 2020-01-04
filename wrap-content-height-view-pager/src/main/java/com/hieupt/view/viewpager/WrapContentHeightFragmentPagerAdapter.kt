package com.hieupt.view.viewpager

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * Created by HieuPT on 1/4/2020.
 */
abstract class WrapContentHeightFragmentPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private var currentPosition = -1

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        super.setPrimaryItem(container, position, `object`)
        if (container is WrapContentHeightViewPager) {
            if (position != currentPosition) {
                val fragment = `object` as Fragment?
                if (fragment?.view != null) {
                    currentPosition = position
                    container.onPageChanged(fragment.view)
                }
            }
        }
    }
}