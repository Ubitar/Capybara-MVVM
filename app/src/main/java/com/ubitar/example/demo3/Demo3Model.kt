package com.ubitar.example.demo3

import com.ubitar.capybara.mvvm.model.BaseModel
//import io.reactivex.Flowable

class Demo3Model(
    private val viewModel: Demo3ViewModel
) : BaseModel(viewModel) {
    //模拟登录
    fun toLogin(account: String, password: String) {
//        Flowable.timer(1000, TimeUnit.MILLISECONDS)
//            .doOnSubscribe { ToastUtils.showShort("加载中") }
//            .subscribe({
//                viewModel.afterLoginSuccess()
//            }, {
//                println(it)
//            }).isDisposed
    }

    override fun onCleared() {
        super.onCleared()
    }

}