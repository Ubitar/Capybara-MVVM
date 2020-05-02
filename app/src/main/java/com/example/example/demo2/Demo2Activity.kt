package com.example.example.demo2

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import com.blankj.utilcode.util.ToastUtils
import com.capybara.mvvm.activity.BaseActivity
import com.example.example.BR
import com.example.example.R
import com.example.example.databinding.ActivityDemo2Binding

class Demo2Activity: BaseActivity<ActivityDemo2Binding, Demo2ViewModel>() {
    override fun getLayoutId(inflater: LayoutInflater, savedInstanceState: Bundle?): Int = R.layout.activity_demo2

    override fun getViewModelId(): Int =BR.viewModel


    /**
     * 下方函数运行顺序（从上往下顺序运行）
     *
     *  initParams()
     *  initViewModelParams()
     *  initView()
     *  onBindObservable()
     *  ViewModel.initEvent()
     *  ViewModel.initData()
     *
     */
    override fun initParams() {
        super.initParams()
        //用于接收并处理从上一个界面传递过来的数据
    }

    override fun initViewModelParams() {
        super.initViewModelParams()
        //如果有需要，可以通过这个函数把初始数据传到ViewModel
        viewModel.valueFromActivity = "注入的值"
    }

    override fun onBindObservable() {
        super.onBindObservable()
        viewModel.actions2.customAction.observe(this, Observer {
            ToastUtils.showShort("Activity收到了来自ViewModel的信息 $it")
        })
    }
}