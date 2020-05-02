package com.capybara.mvvm.vm

import android.app.Application
import com.capybara.mvvm.model.BaseModel
import com.capybara.mvvm.vm.base.BaseActivityViewModel

class EmptyActivityViewModel(application: Application) :
    BaseActivityViewModel<BaseModel>(
        application
    ) {

    override fun getModel(): Class<BaseModel>? = null

}