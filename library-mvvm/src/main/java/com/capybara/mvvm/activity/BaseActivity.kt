package com.capybara.mvvm.activity

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.blankj.utilcode.util.KeyboardUtils
import com.capybara.mvvm.common.ActivityManager
import com.capybara.mvvm.vm.base.BaseActivityViewModel
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator

abstract class BaseActivity<V : ViewDataBinding, VM : BaseActivityViewModel<*>> :
    BaseMvvMActivity<V, VM>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityManager.getManager().addActivity(this)
        fragmentAnimator = DefaultHorizontalAnimator()
        initParams()
        initViewModelParams()
        initView()
        viewModel.initEvent(this)
        viewModel.initData()
    }

    override fun onCreatedViewModel() {
        super.onCreatedViewModel()
        initDaggerInject()
    }

    override fun onBeforeObservable() {
        super.onBeforeObservable()
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityManager.getManager().finishActivity(this)
    }

    override fun finish() {
        KeyboardUtils.hideSoftInput(this)
        super.finish()
    }

    fun getActivity(): BaseActivity<V, VM> {
        return this
    }

    /** 初始化页面参数  */
    open fun initParams() {

    }

    /** 初始化或传递ViewModel的参数  */
    open fun initViewModelParams() {

    }

    /** 初始化视图  */
    open fun initView() {

    }

    /** Dagger注入 */
    open fun initDaggerInject(){

    }


}