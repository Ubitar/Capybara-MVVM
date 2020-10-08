package com.ubitar.capybara.mvvm.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.ubitar.capybara.mvvm.vm.base.BaseFragmentViewModel

/**
 * Created by laohuang on 2018/9/9.
 */

abstract class BaseFragment<V : ViewDataBinding, VM : BaseFragmentViewModel<*>> :
    BaseMvvMFragment<V, VM>() {

    protected var isFirstVisible = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        return attachToSwipeBack(view!!)
    }

    override fun onCreatedViewModel() {
        super.onCreatedViewModel()
        initDaggerInject()
    }

    override fun onBeforeObservable() {
        super.onBeforeObservable()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModelParams()
        initView()
    }

    override fun onSupportVisible() {
        super.onSupportVisible()
    }

    override fun onSupportInvisible() {
        super.onSupportInvisible()
    }

    override fun onEnterAnimationEnd(savedInstanceState: Bundle?) {
        super.onEnterAnimationEnd(savedInstanceState)
        if (isInitDataAfterAnimation() && isFirstVisible) {
            viewModel.initData()
            isFirstVisible = false
        }
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        viewModel.initEvent(this)
        if (!isInitDataAfterAnimation()) {
            viewModel.initData()
            isFirstVisible = false
        }
    }

    /** 是否再加载完动画后才开始加载数据，这样是为了防止卡顿 ，默认：加载动画的同时调用 initData() */
    open fun isInitDataAfterAnimation(): Boolean {
        return false
    }

    /** 初始化页面参数  */
    open fun initParams() {

    }

    /** Dagger注入 */
    open fun initDaggerInject(){

    }

    /** 初始化或传递ViewModel的参数  */
    open fun initViewModelParams() {

    }

    /** 初始化视图  */
    open fun initView() {

    }


}
