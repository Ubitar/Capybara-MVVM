package com.ubitar.capybara.mvvm.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

object ViewPager2Adapter {


    @JvmStatic
    @BindingAdapter(value = ["setCurrentItem", "isSmooth"], requireAll = false)
    fun setCurrentItem(view: ViewPager2, position: Int, isSmooth: Boolean = true) {
        view.setCurrentItem(position, isSmooth)
    }

    @JvmStatic
    @BindingAdapter("setOffscreenPageLimit")
    fun setOffscreenPageLimit(view: ViewPager2, limit: Int) {
        view.offscreenPageLimit = limit
    }

    @JvmStatic
    @BindingAdapter("setAdapter")
    fun setAdapter(view: ViewPager2, adapter: FragmentStateAdapter?) {
        view.adapter = adapter
    }

    @JvmStatic
    @BindingAdapter("setAdapter")
    fun setAdapter(view: ViewPager2, adapter: RecyclerView.Adapter<*>?) {
        view.adapter = adapter
    }

    @JvmStatic
    @BindingAdapter("setUserInputEnabled")
    fun setAdapter(view: ViewPager2, isEnable: Boolean) {
        view.isUserInputEnabled = isEnable
    }

    @JvmStatic
    @BindingAdapter("registerOnPageChangeCallback")
    fun registerOnPageChangeCallback(view: ViewPager2, callback: ViewPager2.OnPageChangeCallback?) {
        if (callback != null) view.registerOnPageChangeCallback(callback)
    }

    @JvmStatic
    @BindingAdapter("overScrollMode")
    fun setOverScrollMode(view: ViewPager2, overScrollMode: Int) {
        val field = view.javaClass.getDeclaredField("mRecyclerView");
        // 参数值为true，打开禁用访问控制检查
        //setAccessible(true) 并不是将方法的访问权限改成了public，而是取消java的权限控制检查。
        //所以即使是public方法，其accessible 属相默认也是false
        field.isAccessible = true;
        (field.get(view) as RecyclerView).overScrollMode = overScrollMode
    }

}