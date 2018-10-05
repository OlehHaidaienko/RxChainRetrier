package com.rxchainretrier.base

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.material.snackbar.Snackbar
import com.rxchainretrier.BuildConfig
import com.rxchainretrier.provider.AppProvider

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity(), BaseActionListener {
    companion object {
        const val ERROR_ACTION = "${BuildConfig.APPLICATION_ID}.action.ERROR"
    }

    protected abstract val layoutId : Int
    protected lateinit var binding : B
    private var snackbar : Snackbar? = null
    private val chainErrorReceiver = object : BroadcastReceiver() {
        override fun onReceive(context : Context?, intent : Intent?) {
            if (intent?.action == ERROR_ACTION) {
                if (snackbar == null) {
                    snackbar = Snackbar.make(binding.root, "Error happened", Snackbar.LENGTH_INDEFINITE)
                            .setAction("Try again") {
                                snackbar = null
                                AppProvider.provideRetrySubject().onNext(Unit)
                            }
                    snackbar?.show()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
    }

    override fun onDisplayMessage(text : String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter(ERROR_ACTION)
        LocalBroadcastManager.getInstance(this).registerReceiver(chainErrorReceiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(chainErrorReceiver)
    }
}