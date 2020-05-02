package com.capybara.mvvm.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.*

import com.capybara.mvvm.IView
import com.capybara.mvvm.vm.base.BaseActivityViewModel
import java.lang.reflect.ParameterizedType

abstract class BaseMvvMActivity<V : ViewDataBinding, VM : BaseActivityViewModel<*>> :
    BaseSwipeBackActivity(), IView {
    protected lateinit var binding: V
    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //创建并获取Databinding对象
        binding = getDataBinding(LayoutInflater.from(this), null, savedInstanceState)
        //创建并获取ViewModel对象
        viewModel = getViewModel()

        //关联databinding和ViewModel，是数据驱动的关键
        binding.setVariable(getViewModelId(), viewModel)
        binding.lifecycleOwner = this

        //让ViewModel监听Activity的生命周期
        viewModel.injectLifecycleOwner(this)

        //Activity注册ViewModel数据内容监听器，用于ViewModel通知View层的操作
        onBindObservable()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }

    /**
     * 注册ViewModel数据监听器
     */
    override fun onBindObservable() {
        viewModel.actions.finishAction.observe(this, Observer {
            finish()
        })
        viewModel.actions.finishAfterTransitionAction.observe(this, Observer {
            ActivityCompat.finishAfterTransition(this)
        })
        viewModel.actions.onBackPressedSupportAction.observe(this, Observer {
            onBackPressedSupport()
        })
        viewModel.actions.setResultAction.observe(this, Observer {
            setResult(it.result, it.data)
        })
        viewModel.actions.postAction.observe(this, Observer {
            post(it)
        })
        viewModel.actions.loadRootFragmentAction.observe(this, Observer {
            loadRootFragment(it.containerId, it.toFragment)
        })
        viewModel.actions.startAction.observe(this, Observer {
            if (it.launchMode == null) start(it.toFragment)
            else start(it.toFragment, it.launchMode)
        })
        viewModel.actions.startWithPopToAction.observe(this, Observer {
            startWithPopTo(it.toFragment, it.targetFragmentClass, it.includeTargetFragment)
        })
        viewModel.actions.popAction.observe(this, Observer {
            pop()
        })
        viewModel.actions.popToAction.observe(this, Observer {
            if (it.afterPopTransactionRunnable == null)
                popTo(it.targetFragmentClass, it.includeTargetFragment)
            else if (it.popAnim == null)
                popTo(
                    it.targetFragmentClass,
                    it.includeTargetFragment,
                    it.afterPopTransactionRunnable
                )
            else popTo(
                it.targetFragmentClass,
                it.includeTargetFragment,
                it.afterPopTransactionRunnable,
                it.popAnim
            )
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
        return DataBindingUtil.setContentView(this, getLayoutId(inflater, savedInstanceState))
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
            AndroidViewModel::class.java
        } as Class<T>
        return ViewModelProvider(this).get(modelClass)
    }

}