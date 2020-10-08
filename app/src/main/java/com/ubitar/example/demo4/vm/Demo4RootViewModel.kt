package com.ubitar.example.demo4.vm

import android.app.Application
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.ubitar.capybara.mvvm.action.FragmentActions
import com.ubitar.capybara.mvvm.model.BaseModel
import com.ubitar.capybara.mvvm.vm.base.BaseFragmentViewModel

class Demo4RootViewModel(application: Application) : BaseFragmentViewModel<BaseModel>(application) {

    var currentItem = MutableLiveData(0)

    override fun getModel(): Class<BaseModel>? = null

    /** 创建自己的业务的Demo1Actions ，并转达给父类 ，若无需Actions，传NUll即可*/
    override fun onCreateActions(): FragmentActions? =null

    fun onClickBtn1(view: View) {
        currentItem.value = 0
    }

    fun onClickBtn2(view: View) {
        currentItem.value = 1
    }

    fun onClickBtn3(view: View) {
        currentItem.value = 2
    }

}