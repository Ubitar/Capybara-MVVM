package com.capybara.mvvm.vm.base

import android.app.Application
import com.capybara.mvvm.action.DialogActions
import com.capybara.mvvm.model.BaseModel

abstract class BaseDialogViewModel<M : BaseModel>(application: Application) : BaseViewModel<M>(application) {

    var actions = DialogActions()
        private set

    fun post(runnable: Runnable) {
        actions.postAction.call(runnable)
    }

    fun dismiss() {
        actions.dismissAction.call()
    }

    fun dismissAllowingStateLoss() {
        actions.dismissAllowingStateLossAction.call()
    }

}