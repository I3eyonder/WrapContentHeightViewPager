package com.hieupt.wrapcontentviewpagerdemo.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.hieupt.view.viewpager.WrapContentHeightFragmentStatePagerAdapter

/**
 * Created by HieuPT on 1/3/2020.
 */
class MyFragmentStateAdapter(fm: FragmentManager) : WrapContentHeightFragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> Fragment1()
        1 -> Fragment2()
        else -> Fragment3()
    }

    override fun getCount(): Int = 3
}