package com.capybara.mvvm.vm.base

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.capybara.mvvm.action.FragmentActions
import com.capybara.mvvm.model.BaseModel
import me.yokeyword.fragmentation.ISupportFragment

abstract class BaseFragmentViewModel<M : BaseModel>(application: Application) :
    BaseViewModel<M>(application) {

    private lateinit var baseActions: FragmentActions

    fun onBackPressedSupport() {
        baseActions.onBackPressedSupportAction.call()
    }

    fun post(runnable: Runnable) {
        baseActions.postAction.call(runnable)
    }


    /**
     * 类似 [Activity.setResult]
     *
     *
     * Similar to [Activity.setResult]
     *
     * @see .startForResult
     */
    fun setFragmentResult(resultCode: Int, bundle: Bundle) {
        baseActions.setFragmentResultAction.call(
            FragmentActions.SetFragmentResultAction.SetFragmentResult(
                resultCode,
                bundle
            )
        )
    }

    /**
     * 添加NewBundle,用于启动模式为SingleTask/SingleTop时
     *
     * @see .start
     */
    fun putNewBundle(newBundle: Bundle) {
        baseActions.putNewBundleAction.call(newBundle)
    }

    /**
     * 加载根Fragment, 即Activity内的第一个Fragment 或 Fragment内的第一个子Fragment
     *
     * @param containerId 容器id
     * @param toFragment  目标Fragment
     */
    fun loadRootFragment(containerId: Int, toFragment: ISupportFragment) {
        baseActions.loadRootFragmentAction.call(
            FragmentActions.LoadRootFragmentAction.LoadRootFragment(
                containerId,
                toFragment
            )
        )
    }

    fun loadRootFragment(
        containerId: Int,
        toFragment: ISupportFragment,
        addToBackStack: Boolean,
        allowAnim: Boolean
    ) {
        baseActions.loadRootFragmentAction.call(
            FragmentActions.LoadRootFragmentAction.LoadRootFragment(
                containerId,
                toFragment,
                addToBackStack,
                allowAnim
            )
        )
    }

    fun start(toFragment: ISupportFragment) {
        baseActions.startAction.call(FragmentActions.StartAction.Start(toFragment))
    }

    fun start(toFragment: ISupportFragment, fromParentFragment: Boolean) {
        baseActions.startAction.call(FragmentActions.StartAction.Start(toFragment, fromParentFragment))
    }

    /**
     * @param launchMode Similar to Activity's LaunchMode.
     */
    fun start(
        toFragment: ISupportFragment,
        fromParentFragment: Boolean,
        @ISupportFragment.LaunchMode launchMode: Int
    ) {
        baseActions.startAction.call(
            FragmentActions.StartAction.Start(
                toFragment,
                fromParentFragment,
                launchMode
            )
        )
    }

    /**
     * Launch an fragment for which you would like a result when it poped.
     */
    fun startForResult(toFragment: ISupportFragment, requestCode: Int) {
        baseActions.startForResultAction.call(
            FragmentActions.StartForResultAction.StartForResult(
                toFragment,
                requestCode
            )
        )
    }


    /**
     * Start the target Fragment and pop itself
     */
    fun startWithPop(toFragment: ISupportFragment) {
        baseActions.startWithPopAction.call(toFragment)
    }

    /**
     * @see .popTo
     * @see .start
     */
    fun startWithPopTo(
        toFragment: ISupportFragment,
        targetFragmentClass: Class<*>,
        includeTargetFragment: Boolean
    ) {
        baseActions.startWithPopToAction.call(
            FragmentActions.StartWithPopToAction.StartWithPopTo(
                toFragment,
                targetFragmentClass,
                includeTargetFragment
            )
        )
    }

    fun replaceFragmentAction(toFragment: ISupportFragment, addToBackStack: Boolean) {
        baseActions.replaceFragmentAction.call(
            FragmentActions.ReplaceFragmentAction.ReplaceFragment(
                toFragment,
                addToBackStack
            )
        )
    }

    fun pop() {
        baseActions.popAction.call()
    }

    /**
     * Pop the last fragment transition from the manager's fragment
     * back stack.
     *
     *
     * 出栈到目标fragment
     *
     * @param targetFragmentClass   目标fragment
     * @param includeTargetFragment 是否包含该fragment
     */
    fun popTo(targetFragmentClass: Class<*>, includeTargetFragment: Boolean) {
        baseActions.popToAction.call(
            FragmentActions.PopToAction.PopTo(
                targetFragmentClass,
                includeTargetFragment
            )
        )
    }


    fun getBaseActions(): FragmentActions {
        return baseActions
    }

    fun injectBaseActions(actions: FragmentActions?) {
        baseActions = actions?:FragmentActions()
    }

    /** 创建自己的Action，并继承FragmentActions */
    abstract fun onCreateActions(): FragmentActions?
}