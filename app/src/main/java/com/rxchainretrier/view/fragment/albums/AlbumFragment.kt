package com.rxchainretrier.view.fragment.albums

import com.rxchainretrier.R
import com.rxchainretrier.base.BaseFragment
import com.rxchainretrier.databinding.FragmentAlbumBinding

class AlbumFragment : BaseFragment<AlbumViewModel, FragmentAlbumBinding>() {
    override val layoutId : Int
        get() = R.layout.fragment_album
    override val viewModelClass : Class<AlbumViewModel>
        get() = AlbumViewModel::class.java
}