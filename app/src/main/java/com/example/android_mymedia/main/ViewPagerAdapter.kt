package com.example.android_mymedia.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.android_mymedia.home.HomeFragment
import com.example.android_mymedia.my_video.MyVideoFragment
import com.example.android_mymedia.search.SearchFragment


class ViewPagerAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    private val fragments = mutableListOf<Fragment>()

    init {
        fragments.apply {
            add(HomeFragment())
            add(SearchFragment())
            add(MyVideoFragment())
        }
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}