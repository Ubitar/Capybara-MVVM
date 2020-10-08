package com.ubitar.capybara.mvvm.vm

import android.app.Application
import com.ubitar.capybara.mvvm.action.DialogActions
import com.ubitar.capybara.mvvm.model.BaseModel
import com.ubitar.capybara.mvvm.vm.base.BaseDialogViewModel

class EmptyDialogViewModel(application: Application) :
    BaseDialogViewModel<BaseModel>(
        application
    ) {
     override fun getModel(): Class<BaseModel>? =null

    override fun onCreateActions(): DialogActions? =null

}