package com.capybara.mvvm

import androidx.lifecycle.LifecycleOwner

interface IViewModel<M:IModel> {
    /**
     * 注入Lifecycle生命周期
     */
    fun injectLifecycleOwner(lifecycle: LifecycleOwner)

    /** 初始化事件 */
    fun initEvent()

    /** 初始化数据  */
    fun initData()

    fun getModel(): Class<M>?

}