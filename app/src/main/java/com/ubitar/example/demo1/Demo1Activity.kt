package com.ubitar.example.demo1

import android.os.Bundle
import android.view.LayoutInflater
import com.ubitar.capybara.mvvm.activity.BaseActivity
import com.ubitar.example.BR
import com.ubitar.example.R
import com.ubitar.example.databinding.ActivityDemo1Binding

class Demo1Activity: BaseActivity<ActivityDemo1Binding, Demo1ViewModel>() {

    /** 你的布局Id **/
    override fun getLayoutId(inflater: LayoutInflater, savedInstanceState: Bundle?): Int = R.layout.activity_demo1

    /** 布局中ViewModel的Name  */
    override fun getViewModelId(): Int =BR.viewModelName

    /**
     * 下方函数运行顺序（从上往下顺序运行）
     *
     *  initParams()
     *  onCreatedViewModel()  /   initDaggerInject()
     *  onBeforeObservable()
     *  onBindObservable()
     *  initViewModelParams()
     *  initView()
     *  ViewModel.initEvent()
     *  ViewModel.initData()
     *
     */
    //用于接收并处理从上一个界面传递过来的数据
    override fun initParams() {
        super.initParams()
    }

    override fun onCreatedViewModel() {
        super.onCreatedViewModel()
    }

    //这里可以进行Dagger注入
    override fun initDaggerInject() {
        super.initDaggerInject()
    }

    override fun onBeforeObservable() {
        super.onBeforeObservable()
    }


    //如果有需要，可以通过这个函数把初始数据传到ViewModel
    override fun initViewModelParams() {
        super.initViewModelParams()
        viewModel.count.value = 100
    }

    // 初始化RecyclerView或者其他View
    override fun initView() {
        super.initView()
    }

    // 注册ViewModel中变量值的改变，可用于ViewModel向View传递信息或操作
    override fun onBindObservable() {
        super.onBindObservable()
    }
}