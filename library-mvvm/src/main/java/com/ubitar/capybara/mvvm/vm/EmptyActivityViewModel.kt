package com.ubitar.capybara.mvvm.vm

import android.app.Application
import com.ubitar.capybara.mvvm.action.ActivityActions
import com.ubitar.capybara.mvvm.model.BaseModel
import com.ubitar.capybara.mvvm.vm.base.BaseActivityViewModel

class EmptyActivityViewModel(application: Application) :
    BaseActivityViewModel<BaseModel>(
        application
    ) {

    override fun getModel(): Class<BaseModel>? = null

    override fun onCreateActions(): ActivityActions? =null

}