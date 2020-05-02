package com.example.example.demo3

import android.os.Bundle
import android.view.LayoutInflater
import com.capybara.mvvm.activity.BaseActivity
import com.example.example.BR
import com.example.example.R
import com.example.example.databinding.ActivityDemo3Binding

class Demo3Activity : BaseActivity<ActivityDemo3Binding, Demo3ViewModel>() {
    override fun getLayoutId(inflater: LayoutInflater, savedInstanceState: Bundle?): Int =
        R.layout.activity_demo3

    override fun getViewModelId(): Int = BR.viewModel


}