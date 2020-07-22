package com.capybara.mvvm.vm.base

import android.app.Application
import android.content.Intent
import com.capybara.mvvm.action.ActivityActions
import com.capybara.mvvm.model.BaseModel
import me.yokeyword.fragmentation.ISupportFragment

abstract class BaseActivityViewModel<M : BaseModel>(application: Application) :
    BaseViewModel<M>(application) {

    private lateinit var baseActions: ActivityActions

    fun finish() {
        baseActions.finishAction.call()
    }

    fun finishAfterTransition() {
        baseActions.finishAfterTransitionAction.call()
    }

    fun onBackPressedSupport() {
        baseActions.onBackPressedSupportAction.call()
    }

    fun setResult(resultCode: Int) {
        baseActions.setResultAction.call(ActivityActions.SetResultAction.SetResult(resultCode))
    }

    fun setResult(resultCode: Int, data: Intent) {
        baseActions.setResultAction.call(ActivityActions.SetResultAction.SetResult(resultCode, data))
    }

    fun post(runnable: Runnable) {
        baseActions.postAction.call(runnable)
    }

    fun loadRootFragment(containerId: Int, toFragment: ISupportFragment) {
        baseActions.loadRootFragmentAction.call(
            ActivityActions.LoadRootFragmentAction.LoadRootFragment(
                containerId,
                toFragment
            )
        )
    }

    fun start(toFragment: ISupportFragment) {
        baseActions.startAction.call(ActivityActions.StartAction.Start(toFragment))
    }

    fun start(toFragment: ISupportFragment, @ISupportFragment.LaunchMode launchMode: Int) {
        baseActions.startAction.call(ActivityActions.StartAction.Start(toFragment, false, launchMode))
    }

    fun startWithPopTo(
        toFragment: ISupportFragment,
        targetFragmentClass: Class<*>,
        includeTargetFragment: Boolean
    ) {
        baseActions.startWithPopToAction.call(
            ActivityActions.StartWithPopToAction.StartWithPopTo(
                toFragment,
                targetFragmentClass,
                includeTargetFragment
            )
        )
    }

    fun pop() {
        baseActions.popAction.call()
    }

    fun popTo(targetFragmentClass: Class<*>, includeTargetFragment: Boolean) {
        baseActions.popToAction.call(ActivityActions.PopToAction.PopTo(targetFragmentClass, includeTargetFragment))
    }

    fun popTo(
        targetFragmentClass: Class<*>,
        includeTargetFragment: Boolean,
        afterPopTransactionRunnable: Runnable
    ) {
        baseActions.popToAction.call(
            ActivityActions.PopToAction.PopTo(
                targetFragmentClass,
                includeTargetFragment,
                afterPopTransactionRunnable
            )
        )
    }

    fun popTo(
        targetFragmentClass: Class<*>,
        includeTargetFragment: Boolean,
        afterPopTransactionRunnable: Runnable,
        popAnim: Int
    ) {
        baseActions.popToAction.call(
            ActivityActions.PopToAction.PopTo(
                targetFragmentClass,
                includeTargetFragment,
                afterPopTransactionRunnable,
                popAnim
            )
        )
    }


    fun getBaseActions(): ActivityActions {
        return baseActions
    }

    internal fun injectBaseActions(actions: ActivityActions?) {
        baseActions = actions ?: ActivityActions()
    }

    /** 创建自己的Action，并继承ActionActions */
    abstract fun onCreateActions(): ActivityActions?
}