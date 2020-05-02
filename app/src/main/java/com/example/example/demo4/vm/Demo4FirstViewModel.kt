package com.example.example.demo4.vm

import android.app.Application
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.capybara.mvvm.model.BaseModel
import com.capybara.mvvm.vm.base.BaseFragmentViewModel
import com.example.example.demo4.fragment.Demo4RootFragment

class Demo4FirstViewModel(application: Application) :
    BaseFragmentViewModel<BaseModel>(application) {
    override fun getModel(): Class<BaseModel>? = null

    var position = MutableLiveData(1)

    fun onClickTxt(view: View) {
        start(Demo4RootFragment(),true)
    }

}