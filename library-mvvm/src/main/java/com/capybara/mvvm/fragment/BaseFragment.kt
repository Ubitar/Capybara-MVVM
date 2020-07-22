package com.capybara.mvvm.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.capybara.mvvm.activity.BaseSupportActivity
import com.capybara.mvvm.vm.base.BaseFragmentViewModel

/**
 * Created by laohuang on 2018/9/9.
 */

abstract class BaseFragment<V : ViewDataBinding, VM : BaseFragmentViewModel<*>> :
    BaseMvvMFragment<V, VM>() {

    private val postRefreshEvent = LinkedHashMap<Int,Any?>(10)

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
        viewModel.initEvent()
    }

    override fun onSupportVisible() {
        super.onSupportVisible()
        if (!isFirstVisible)
            for (event in postRefreshEvent) onRefresh(event.key,event.value)
        postRefreshEvent.clear()
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
        if (!isInitDataAfterAnimation()) {
            viewModel.initData()
            isFirstVisible = false
        }
    }

    /**
     * 在fragment有空(出现在用户面前)的时候再刷新
     */
    fun postRefresh(type:Int,data:Any?=null) {
        if (isSupportVisible) onRefresh(type,data)
        else postRefreshEvent[type]=data
    }

    /**
     * 立即刷新 ，推荐使用PostRefresh
     */
    open fun onRefresh(type:Int, data:Any?) {}

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
