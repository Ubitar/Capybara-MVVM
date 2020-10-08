package com.ubitar.example.demo4

import android.os.Bundle
import android.view.LayoutInflater
import com.ubitar.capybara.mvvm.activity.BaseActivity
import com.ubitar.capybara.mvvm.vm.EmptyActivityViewModel
import com.ubitar.example.R
import com.ubitar.example.BR
import com.ubitar.example.databinding.ActivityDemo4Binding
import com.ubitar.example.demo4.fragment.Demo4RootFragment

class Demo4Activity : BaseActivity<ActivityDemo4Binding, EmptyActivityViewModel>() {
    override fun getLayoutId(inflater: LayoutInflater, savedInstanceState: Bundle?): Int =
        R.layout.activity_demo4

    override fun getViewModelId(): Int = BR.viewModel

    override fun initView() {
        super.initView()
        loadRootFragment(R.id.layoutContainer, Demo4RootFragment())
    }
}