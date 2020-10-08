package com.ubitar.capybara.mvvm.dialog

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.ubitar.capybara.mvvm.IView
import com.ubitar.capybara.mvvm.R
import com.ubitar.capybara.mvvm.vm.base.BaseDialogViewModel
import java.lang.reflect.ParameterizedType

abstract class BaseMvvMDialogFragment<V : ViewDataBinding, VM : BaseDialogViewModel<*>> :
    DialogFragment(),
    IView {

    protected lateinit var binding: V
    protected lateinit var viewModel: VM

    private val handler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val content = LinearLayout(context)
        inflater.inflate(getLayoutId(inflater,savedInstanceState), content, true)
        return content
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return  Dialog(context!!, R.style.BaseDialogTheme)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //创建并获取Databinding对象
        binding = getDataBinding(dialog!!.layoutInflater, null, savedInstanceState)
        //创建并获取ViewModel对象
        viewModel = getViewModel()

        //关联databinding和ViewModel，是数据驱动的关键
        binding.setVariable(getViewModelId(), viewModel)
        binding.lifecycleOwner = this

        //让ViewModel监听Activity的生命周期
        viewModel.injectLifecycleOwner(this)

        //创建ViewModel后
        onCreatedViewModel()
        viewModel.injectBaseActions(viewModel.onCreateActions())
        // 注册内容监听前 */
        onBeforeObservable()
        //Activity注册ViewModel数据内容监听器，用于ViewModel通知View层的操作
        onBindObservable()
    }


    /**
     * 创建ViewModel后
     */
    override fun onCreatedViewModel() {
    }

    /**
     * 在注册ViewModel与View的回调事件前
     */
    override fun onBeforeObservable() {
    }

    override fun onDestroyView() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroyView()
        binding.unbind()
    }

    /**
     * 注册ViewModel数据监听器
     */
    override fun onBindObservable() {
        viewModel.getBaseActions().postAction.observe(binding.lifecycleOwner!!, Observer {
            handler.post(it)
        })
        viewModel.getBaseActions().dismissAction.observe(binding.lifecycleOwner!!, Observer {
            dismiss()
        })
        viewModel.getBaseActions().dismissAllowingStateLossAction.observe(
            binding.lifecycleOwner!!,
            Observer {
                dismissAllowingStateLoss()
            })
    }

    /**
     * 创建并获取DataBinding
     */
    override fun <T : ViewDataBinding> getDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): T {
        return DataBindingUtil.bind((view as ViewGroup).getChildAt(0))!!
    }

    /**
     * 创建并获取ViewModel实例
     * */
    override fun <T : ViewModel> getViewModel(): T {
        val type = this.javaClass.genericSuperclass
        val modelClass = if (type is ParameterizedType) {
            type.actualTypeArguments[1] as Class<*>
        } else {
            //如果没有指定泛型参数，则默认使用BaseViewModel
            BaseDialogViewModel::class.java
        } as Class<T>
        return ViewModelProvider(this).get(modelClass)
    }


}