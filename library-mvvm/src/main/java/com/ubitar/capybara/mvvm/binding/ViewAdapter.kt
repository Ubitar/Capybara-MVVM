package com.ubitar.capybara.mvvm.binding

import android.view.MotionEvent
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter

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
    fun onTouch(view: View, listener: OnTouchListener?) {
        view.setOnTouchListener { v: View?, event: MotionEvent? ->
            listener?.onTouch(v, event) ?: false
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
    @BindingAdapter("setSelected")
    fun setSelected(view: View, isSelected: Boolean) {
        view.isSelected = isSelected
    }

    @JvmStatic
    @BindingAdapter("setActivated")
    fun setActivated(view: View, isActivated: Boolean) {
        view.isActivated = isActivated
    }

    @JvmStatic
    @BindingAdapter("setEnabled")
    fun setEnabled(view: View, isEnabled: Boolean) {
        view.isEnabled = isEnabled
    }

    @JvmStatic
    @BindingAdapter("setWeight")
    fun setWeight(view: View, weight: Float) {
        val layoutParams = (view.layoutParams as LinearLayout.LayoutParams)
        layoutParams.weight = weight
        view.layoutParams = layoutParams
    }

    @JvmStatic
    @BindingAdapter("layout_height")
    fun layout_height(view: View, height: Int) {
        val layoutParams = (view.layoutParams as ViewGroup.LayoutParams)
        layoutParams.height = height
        view.layoutParams = layoutParams
    }

    @JvmStatic
    @BindingAdapter("layout_width")
    fun layout_width(view: View, width: Int) {
        val layoutParams = (view.layoutParams as ViewGroup.LayoutParams)
        layoutParams.width = width
        view.layoutParams = layoutParams
    }
}