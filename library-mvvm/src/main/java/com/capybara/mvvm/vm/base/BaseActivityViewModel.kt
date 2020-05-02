package com.capybara.mvvm.vm.base

import android.app.Application
import android.content.Intent
import com.capybara.mvvm.action.ActivityActions
import com.capybara.mvvm.model.BaseModel
import me.yokeyword.fragmentation.ISupportFragment

abstract class BaseActivityViewModel<M : BaseModel>(application: Application) :
    BaseViewModel<M>(application) {

    var actions = ActivityActions()
        private set

    fun finish() {
        actions.finishAction.call()
    }

    fun finishAfterTransition() {
        actions.finishAfterTransitionAction.call()
    }

    fun onBackPressedSupport() {
        actions.onBackPressedSupportAction.call()
    }

    fun setResult(resultCode: Int) {
        actions.setResultAction.call(ActivityActions.SetResultAction.SetResult(resultCode))
    }

    fun setResult(resultCode: Int, data: Intent) {
        actions.setResultAction.call(ActivityActions.SetResultAction.SetResult(resultCode, data))
    }

    fun post(runnable: Runnable) {
        actions.postAction.call(runnable)
    }

    fun loadRootFragment(containerId: Int, toFragment: ISupportFragment) {
        actions.loadRootFragmentAction.call(
            ActivityActions.LoadRootFragmentAction.LoadRootFragment(
                containerId,
                toFragment
            )
        )
    }

    fun start(toFragment: ISupportFragment) {
        actions.startAction.call(ActivityActions.StartAction.Start(toFragment))
    }

    fun start(toFragment: ISupportFragment, @ISupportFragment.LaunchMode launchMode: Int) {
        actions.startAction.call(ActivityActions.StartAction.Start(toFragment, false, launchMode))
    }

    fun startWithPopTo(
        toFragment: ISupportFragment,
        targetFragmentClass: Class<*>,
        includeTargetFragment: Boolean
    ) {
        actions.startWithPopToAction.call(
            ActivityActions.StartWithPopToAction.StartWithPopTo(
                toFragment,
                targetFragmentClass,
                includeTargetFragment
            )
        )
    }

    fun pop() {
        actions.popAction.call()
    }

    fun popTo(targetFragmentClass: Class<*>, includeTargetFragment: Boolean) {
        actions.popToAction.call(ActivityActions.PopToAction.PopTo(targetFragmentClass, includeTargetFragment))
    }

    fun popTo(
        targetFragmentClass: Class<*>,
        includeTargetFragment: Boolean,
        afterPopTransactionRunnable: Runnable
    ) {
        actions.popToAction.call(
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
        actions.popToAction.call(
            ActivityActions.PopToAction.PopTo(
                targetFragmentClass,
                includeTargetFragment,
                afterPopTransactionRunnable,
                popAnim
            )
        )
    }
}