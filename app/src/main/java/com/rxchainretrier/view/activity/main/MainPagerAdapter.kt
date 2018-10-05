package com.rxchainretrier.view.activity.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.rxchainretrier.view.fragment.albums.AlbumFragment
import com.rxchainretrier.view.fragment.users.UsersFragment

class MainPagerAdapter(fm : FragmentManager?) : FragmentPagerAdapter(fm) {
    private val items = arrayOf(
            UsersFragment() to "Users",
            AlbumFragment() to "Album"
    )

    override fun getItem(position : Int) : Fragment = items[position].first

    override fun getCount() : Int = items.size

    override fun getPageTitle(position : Int) : CharSequence? = items[position].second
}