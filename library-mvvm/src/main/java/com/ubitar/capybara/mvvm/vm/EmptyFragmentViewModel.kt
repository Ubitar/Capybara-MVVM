package com.ubitar.capybara.mvvm.vm

import android.app.Application
import com.ubitar.capybara.mvvm.action.FragmentActions
import com.ubitar.capybara.mvvm.model.BaseModel
import com.ubitar.capybara.mvvm.vm.base.BaseFragmentViewModel

class EmptyFragmentViewModel(application: Application) :
    BaseFragmentViewModel<BaseModel>(
        application
    ) {
    override fun getModel(): Class<BaseModel>? = null

    override fun onCreateActions(): FragmentActions? =null

}