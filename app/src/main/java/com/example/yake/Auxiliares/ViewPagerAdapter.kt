package com.example.yake.Auxiliares


import androidx.viewpager.widget.PagerAdapter

class ViewPagerAdapter(manager: androidx.fragment.app.FragmentManager) :
    androidx.fragment.app.FragmentPagerAdapter(manager) {

    private val fragmentList: MutableList<androidx.fragment.app.Fragment> = ArrayList()
    private val titleList: MutableList<String> = ArrayList()

    override fun getItem(position: Int): androidx.fragment.app.Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }


    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

    fun addFragment(fragment: androidx.fragment.app.Fragment, title: String) {
        fragmentList.add(fragment)
        titleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }


}