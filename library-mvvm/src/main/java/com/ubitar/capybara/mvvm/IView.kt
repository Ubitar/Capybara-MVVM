package com.ubitar.capybara.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

interface IView {

    /**
     * 创建ViewModel后
     */
    fun onCreatedViewModel()

    /**
     * 在注册ViewModel与View的回调事件前
     */
    fun onBeforeObservable()

    /**
     * 注册ViewModel于View的回调事件
     */
    fun onBindObservable()

    /** 创建DataBinding  */
    fun <T : ViewDataBinding> getDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): T

    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    fun getLayoutId(inflater: LayoutInflater, savedInstanceState: Bundle?): Int


    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    fun getViewModelId(): Int

    /**
     * 创建ViewModel
     *
     * @param cls
     * @param <T>
     * @return
    </T> */
    fun <T : ViewModel> getViewModel(): T
}