package com.capybara.mvvm.vm

import android.app.Application
import com.capybara.mvvm.action.FragmentActions
import com.capybara.mvvm.model.BaseModel
import com.capybara.mvvm.vm.base.BaseFragmentViewModel

class EmptyFragmentViewModel(application: Application) :
    BaseFragmentViewModel<BaseModel>(
        application
    ) {
    override fun getModel(): Class<BaseModel>? = null

    override fun onCreateActions(): FragmentActions? =null

}