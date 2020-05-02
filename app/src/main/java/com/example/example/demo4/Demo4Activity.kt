package com.example.example.demo4

import android.os.Bundle
import android.view.LayoutInflater
import com.capybara.mvvm.activity.BaseActivity
import com.capybara.mvvm.vm.EmptyActivityViewModel
import com.example.example.R
import com.example.example.BR
import com.example.example.databinding.ActivityDemo4Binding
import com.example.example.demo4.fragment.Demo4RootFragment

class Demo4Activity : BaseActivity<ActivityDemo4Binding, EmptyActivityViewModel>() {
    override fun getLayoutId(inflater: LayoutInflater, savedInstanceState: Bundle?): Int =
        R.layout.activity_demo4

    override fun getViewModelId(): Int = BR.viewModel

    override fun initView() {
        super.initView()
        loadRootFragment(R.id.layoutContainer, Demo4RootFragment())
    }
}