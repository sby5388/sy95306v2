package com.by5388.sy95306.kotlin.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.by5388.sy95306.kotlin.R
import com.by5388.sy95306.kotlin.main.adapter.FragmentAdapter
import com.by5388.sy95306.kotlin.main.fragment.DetailFragment
import com.by5388.sy95306.kotlin.main.fragment.ListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {
    var adapter: FragmentStatePagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        initView()
    }


    private fun initData() {
        val fragments: ArrayList<Fragment> = ArrayList()
        fragments.add(ListFragment.newInstance())
        fragments.add(DetailFragment.newInstance())
        adapter = FragmentAdapter(supportFragmentManager, fragments)
    }

    private fun initView() {
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(this)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == radioButton0.id) {
                viewPager.setCurrentItem(0, true)
            }
            if (checkedId == radioButton1.id) {
                viewPager.setCurrentItem(1, true)
            }

        }
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        if (position==0){
            radioGroup.check(radioButton0.id)
        }else{
            radioGroup.check(radioButton1.id)
        }
    }

}
