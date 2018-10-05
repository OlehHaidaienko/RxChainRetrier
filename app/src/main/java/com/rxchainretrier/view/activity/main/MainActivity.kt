package com.rxchainretrier.view.activity.main

import android.os.Bundle
import com.rxchainretrier.R
import com.rxchainretrier.base.BaseActivity
import com.rxchainretrier.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layoutId : Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding.pager.adapter = MainPagerAdapter(supportFragmentManager)
    }
}
