package com.example.android_mymedia.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.android_mymedia.R
import com.example.android_mymedia.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    private val viewPagerAdapter by lazy {
        ViewPagerAdapter(this@MainActivity)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

    }
    private fun initView() = with(binding) {
        //스와이프 금지
        mainViewpager.run {
            isUserInputEnabled = false
        }
        mainViewpager.adapter = viewPagerAdapter

        //뷰페이저 네비게이션바 연결
        mainViewpager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    mainNvMenu.menu.getItem(position).isChecked = true
                }
            }
        )

        //네비게이션 바 선택 이벤트
        mainNvMenu.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_home -> mainViewpager.currentItem = 0
                R.id.menu_search -> mainViewpager.currentItem = 1
                R.id.menu_profile -> mainViewpager.currentItem = 2
            }
            true
        }
    }
}