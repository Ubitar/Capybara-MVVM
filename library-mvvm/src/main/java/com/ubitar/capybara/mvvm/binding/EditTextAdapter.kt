package com.ubitar.capybara.mvvm.binding

import android.text.TextWatcher
import android.widget.EditText
import androidx.databinding.BindingAdapter

object EditTextAdapter {

    @JvmStatic
    @BindingAdapter("setFocus")
    fun setFocus(editText: EditText, isRequest: Boolean) {
        if (isRequest) {
            editText.setSelection(editText.text.length)
            editText.requestFocus()
        }
        editText.isFocusableInTouchMode = isRequest
    }

    @JvmStatic
    @BindingAdapter("addTextChangedListener")
    fun addTextChangedListener(editText: EditText, textWatcher: TextWatcher) {
        editText.addTextChangedListener(textWatcher)
    }

    @JvmStatic
    @BindingAdapter("addTextChangedListener2")
    fun addTextChangedListener2(editText: EditText, textWatcher: TextWatcher) {
        addTextChangedListener(editText, textWatcher)
    }

    @JvmStatic
    @BindingAdapter("addTextChangedListener3")
    fun addTextChangedListener3(editText: EditText, textWatcher: TextWatcher) {
        addTextChangedListener(editText, textWatcher)
    }

    @JvmStatic
    @BindingAdapter("addTextChangedListener4")
    fun addTextChangedListener4(editText: EditText, textWatcher: TextWatcher) {
        addTextChangedListener(editText, textWatcher)
    }

    @JvmStatic
    @BindingAdapter("addTextChangedListener5")
    fun addTextChangedListener5(editText: EditText, textWatcher: TextWatcher) {
        addTextChangedListener(editText, textWatcher)
    }


}