package com.capybara.mvvm.binding

import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2

object ViewPager2Adapter {

    @JvmStatic
    @BindingAdapter("setCurrentItem")
    fun setCurrentItem(view: ViewPager2, position: Int) {
        view.setCurrentItem(position, false)
    }

    @JvmStatic
    @BindingAdapter("setCurrentItemSmooth")
    fun setCurrentItemSmooth(view: ViewPager2, position: Int) {
        view.setCurrentItem(position, true)
    }

    @JvmStatic
    @BindingAdapter("setOffscreenPageLimit")
    fun setOffscreenPageLimit(view: ViewPager2, limit: Int) {
        view.offscreenPageLimit = limit
    }

    @JvmStatic
    @BindingAdapter("registerOnPageChangeCallback")
    fun registerOnPageChangeCallback(view: ViewPager2, callback: ViewPager2.OnPageChangeCallback) {
        view.registerOnPageChangeCallback(callback)
    }

}