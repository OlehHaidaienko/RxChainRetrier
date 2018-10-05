package com.rxchainretrier.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.rxchainretrier.BR
import com.rxchainretrier.nonNullObserve

abstract class BaseFragment<VM : BaseViewModel, B : ViewDataBinding> : Fragment() {
    protected abstract val layoutId : Int
    protected abstract val viewModelClass : Class<VM>
    protected lateinit var binding : B
    protected val viewModel : VM by lazy {
        ViewModelProviders.of(this).get(viewModelClass)
    }
    protected var baseActionListener : BaseActionListener? = null

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.messageEvent.nonNullObserve(this) {
            baseActionListener?.onDisplayMessage(it)
        }
    }

    override fun onAttach(context : Context?) {
        super.onAttach(context)
        baseActionListener = activity as? BaseActionListener
    }

    override fun onDetach() {
        super.onDetach()
        baseActionListener = null
    }
}