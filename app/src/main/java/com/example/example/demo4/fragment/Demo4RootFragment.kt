package com.example.example.demo4.fragment

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.example.demo4.vm.Demo4RootViewModel
import com.capybara.mvvm.fragment.BaseFragment
import com.example.example.BR
import com.example.example.R
import com.example.example.databinding.FragmentDemo4RootBinding

class Demo4RootFragment : BaseFragment<FragmentDemo4RootBinding, Demo4RootViewModel>() {
    override fun getLayoutId(inflater: LayoutInflater, savedInstanceState: Bundle?): Int =
        R.layout.fragment_demo4_root

    override fun getViewModelId(): Int = BR.viewModel

    override fun initView() {
        super.initView()
        initViewPager2()
    }

    /** 初始化ViewPager */
    private fun initViewPager2() {
        binding.viewPager.offscreenPageLimit = 1
        binding.viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 5

            override fun createFragment(position: Int): Fragment {
                return Demo4FirstFragment.newInstance(position)
            }
        }
    }

}