package com.example.example.demo4.vm

import android.app.Application
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.capybara.mvvm.model.BaseModel
import com.capybara.mvvm.vm.base.BaseFragmentViewModel

class Demo4RootViewModel(application: Application) : BaseFragmentViewModel<BaseModel>(application) {

    var currentItem = MutableLiveData(0)

    override fun getModel(): Class<BaseModel>? = null

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