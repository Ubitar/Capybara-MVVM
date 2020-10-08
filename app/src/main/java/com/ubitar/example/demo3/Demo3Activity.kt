package com.ubitar.example.demo3

import android.os.Bundle
import android.view.LayoutInflater
import com.ubitar.capybara.mvvm.activity.BaseActivity
import com.ubitar.example.BR
import com.ubitar.example.R
import com.ubitar.example.databinding.ActivityDemo3Binding

class Demo3Activity : BaseActivity<ActivityDemo3Binding, Demo3ViewModel>() {
    override fun getLayoutId(inflater: LayoutInflater, savedInstanceState: Bundle?): Int =
        R.layout.activity_demo3

    override fun getViewModelId(): Int = BR.viewModel


}