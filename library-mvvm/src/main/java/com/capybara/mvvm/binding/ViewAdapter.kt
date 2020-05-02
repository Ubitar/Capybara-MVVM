package com.capybara.mvvm.binding

import android.view.MotionEvent
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.View.OnTouchListener
import androidx.databinding.BindingAdapter
import java.util.concurrent.TimeUnit

/**
 * Created by goldze on 2017/6/16.
 */
object ViewAdapter {

    @JvmStatic
    @BindingAdapter("onClick")
    fun onClick(view: View, listener: View.OnClickListener) {
        view.setOnClickListener {
            listener.onClick(view)
        }
    }

    @JvmStatic
    @BindingAdapter("onLongClick")
    fun onLongClick(view: View, listener: View.OnLongClickListener) {
        view.setOnLongClickListener {
            listener.onLongClick(view)
        }
    }

    @JvmStatic
    @BindingAdapter("onFocusChange")
    fun onFocusChange(view: View, listener: OnFocusChangeListener) {
        view.onFocusChangeListener = OnFocusChangeListener { v: View?, hasFocus: Boolean ->
            listener.onFocusChange(v, hasFocus)
        }
    }

    @JvmStatic
    @BindingAdapter("onTouch")
    fun onTouch(view: View, listener: OnTouchListener) {
        view.setOnTouchListener { v: View?, event: MotionEvent? ->
            listener.onTouch(v, event)
        }
    }

    @JvmStatic
    @BindingAdapter("setFocus")
    fun setFocus(view: View, isRequest: Boolean) {
        if (isRequest) {
            view.isFocusableInTouchMode = true
            view.requestFocus()
        } else {
            view.clearFocus()
        }
    }

    @JvmStatic
    @BindingAdapter("setActivated")
    fun setActivated(view: View, isActivated: Boolean) {
        view.isActivated = isActivated
    }
}