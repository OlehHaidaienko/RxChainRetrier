package com.rxchainretrier.view.fragment.users

import com.rxchainretrier.R
import com.rxchainretrier.base.BaseFragment
import com.rxchainretrier.databinding.FragmentUsersBinding

class UsersFragment : BaseFragment<UsersViewModel, FragmentUsersBinding>() {
    override val layoutId : Int
        get() = R.layout.fragment_users
    override val viewModelClass : Class<UsersViewModel>
        get() = UsersViewModel::class.java
}