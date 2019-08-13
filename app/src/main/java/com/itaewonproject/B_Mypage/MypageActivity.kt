package com.itaewonproject.B_Mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.material.tabs.TabLayout
import com.itaewonproject.R

class MypageActivity : AppCompatActivity(){

    private lateinit var tabLayout:TabLayout
    private lateinit var viewPager:ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)
        tabLayout = findViewById(R.id.tabLayout) as TabLayout
        viewPager = findViewById(R.id.viewPager) as ViewPager
        var adapter = TabPagerAdapter(supportFragmentManager,3)
        adapter.addPage(RouteFragment(),"Route")
        adapter.addPage(WishlistFragment(),"Wish List")
        adapter.addPage(ReviewFragment(),"Review")
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }
}
