package com.ubitar.capybara.mvvm.vm.base

import android.app.Application
import com.ubitar.capybara.mvvm.action.DialogActions
import com.ubitar.capybara.mvvm.model.BaseModel

abstract class BaseDialogViewModel<M : BaseModel>(application: Application) : BaseViewModel<M>(application) {

    private lateinit var baseActions: DialogActions

    fun post(runnable: Runnable) {
        baseActions.postAction.call(runnable)
    }

    fun dismiss() {
        baseActions.dismissAction.call()
    }

    fun dismissAllowingStateLoss() {
        baseActions.dismissAllowingStateLossAction.call()
    }


    fun getBaseActions(): DialogActions {
        return baseActions
    }

    fun injectBaseActions(actions: DialogActions?) {
        baseActions = actions?:DialogActions()
    }

    /** 创建自己的Action，并继承DialogActions */
    abstract fun onCreateActions(): DialogActions ?

}