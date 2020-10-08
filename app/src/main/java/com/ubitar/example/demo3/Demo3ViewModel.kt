package com.ubitar.example.demo3

import android.app.Application
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.ToastUtils
import com.ubitar.capybara.mvvm.action.ActivityActions
import com.ubitar.capybara.mvvm.vm.base.BaseActivityViewModel

class Demo3ViewModel(application: Application) : BaseActivityViewModel<Demo3Model>(application) {

    override fun getModel(): Class<Demo3Model>? = Demo3Model::class.java

    /** 创建自己的业务的Demo1Actions ，并转达给父类 ，若无需Actions，传NUll即可*/
    override fun onCreateActions(): ActivityActions? =null

    var account = MutableLiveData<String>("")
    var password = MutableLiveData<String>("")

    fun onClickLogin(view: View) {
        model.toLogin(account.value ?: "", password.value ?: "")
    }

    fun afterLoginSuccess() {
        ToastUtils.showShort("登陆成功")
    }

}