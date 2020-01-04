package com.hieupt.wrapcontentviewpagerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hieupt.wrapcontentviewpagerdemo.viewpager.MyFragmentStateAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager.adapter = MyFragmentStateAdapter(supportFragmentManager)
    }
}
