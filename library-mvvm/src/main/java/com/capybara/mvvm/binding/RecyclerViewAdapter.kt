package com.capybara.mvvm.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object RecyclerViewAdapter {

    @JvmStatic
    @BindingAdapter("setItemAnimator")
    fun setItemAnimator(recyclerView: RecyclerView, animator: RecyclerView.ItemAnimator?) {
        recyclerView.itemAnimator = animator
    }

    @JvmStatic
    @BindingAdapter("setAdapter")
    fun setAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>?) {
        recyclerView.adapter = adapter
    }

}