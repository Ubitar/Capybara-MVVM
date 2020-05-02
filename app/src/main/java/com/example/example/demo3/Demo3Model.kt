package com.example.example.demo3

import com.blankj.utilcode.util.ToastUtils
import com.capybara.mvvm.model.BaseModel
import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

class Demo3Model(
    private val viewModel: Demo3ViewModel
) : BaseModel(viewModel) {
    //模拟登录
    fun toLogin(account: String, password: String) {
        Flowable.timer(1000, TimeUnit.MILLISECONDS)
            .doOnSubscribe { ToastUtils.showShort("加载中") }
                //这个是AutoDispose  如果要Rxlifecycle你要自己更换
//            .`as`(AutoDisposeUtil.fromOnDestroy(viewModel.lifecycle.get()!!))
            .subscribe({
                viewModel.afterLoginSuccess()
            }, {
                println(it)
            }).isDisposed
    }

    override fun onCleared() {
        super.onCleared()
    }

}