package com.by5388.sy95306.kotlin.main.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * @author  by5388  on 2019/1/5.
 */
class FragmentAdapter(fragmentManager: FragmentManager, private val fragments: List<Fragment>) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int) = fragments[position]

    override fun getCount() = fragments.size
}