package com.capybara.mvvm.vm.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import com.capybara.mvvm.IViewModel
import com.capybara.mvvm.model.BaseModel
import java.lang.ref.WeakReference

abstract class BaseViewModel<M : BaseModel>(application: Application) :
    AndroidViewModel(application),
    IViewModel<M> {

    protected lateinit var model: M

    init {
        createModel()
    }

    //弱引用持有
    lateinit var lifecycle: WeakReference<LifecycleOwner>

    override fun injectLifecycleOwner(lifecycle: LifecycleOwner) {
        this.lifecycle = WeakReference(lifecycle)
    }

    /** 初始化事件 */
    override fun initEvent() {

    }

    /** 初始化数据  */
    override fun initData() {

    }

    /** 创建数据层 Model */
    private fun createModel() {
        model = getModel()?.constructors?.get(0)?.newInstance(this) as M? ?: BaseModel(
            this
        ) as M
    }

    override fun onCleared() {
        super.onCleared()
        lifecycle.clear()
    }
}