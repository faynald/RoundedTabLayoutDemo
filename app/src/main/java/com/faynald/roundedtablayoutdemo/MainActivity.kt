package com.faynald.roundedtablayoutdemo

import android.content.Context
import android.graphics.drawable.StateListDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.faynald.roundedtablayoutdemo.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTab()

    }
    private fun setTab() {
        // setup viewPager
        binding.viewPager.adapter = ReportsTabAdapter(this)
        binding.viewPager.offscreenPageLimit = 2
        val tabTitle = resources.getStringArray(R.array.tab_laporan_title)
        TabLayoutMediator(
            binding.tabLayout, binding.viewPager
        ) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()

        // setup customization for tab
        val tabCount: Int = binding.tabLayout.tabCount

        for (i in 0 until tabCount) {
            val tabView: View = (binding.tabLayout.getChildAt(0) as ViewGroup).getChildAt(i)
            tabView.requestLayout()
            ViewCompat.setBackground(tabView,setImageButtonStateNew(this@MainActivity));
            ViewCompat.setPaddingRelative(tabView, tabView.paddingStart, tabView.paddingTop, tabView.paddingEnd, tabView.paddingBottom);
        }
    }

    private fun setImageButtonStateNew(mContext: Context): StateListDrawable {
        val states = StateListDrawable()
        states.addState(intArrayOf(android.R.attr.state_selected), ContextCompat.getDrawable(mContext, R.drawable.tab_bg_normal_blue))
        states.addState(intArrayOf(-android.R.attr.state_selected), ContextCompat.getDrawable(mContext, R.drawable.tab_bg_normal))

        return states
    }

}