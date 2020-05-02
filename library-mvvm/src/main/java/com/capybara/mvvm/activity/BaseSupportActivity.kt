package com.capybara.mvvm.activity

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity

import me.yokeyword.fragmentation.ExtraTransaction
import me.yokeyword.fragmentation.ISupportActivity
import me.yokeyword.fragmentation.ISupportFragment
import me.yokeyword.fragmentation.SupportActivityDelegate
import me.yokeyword.fragmentation.SupportHelper
import me.yokeyword.fragmentation.anim.FragmentAnimator

abstract class BaseSupportActivity : AppCompatActivity(), ISupportActivity {
    protected val mSupportActivityDelegate = SupportActivityDelegate(this)

    /**
     * 得到位于栈顶Fragment
     */
    val topFragment: ISupportFragment
        get() = SupportHelper.getTopFragment(supportFragmentManager)

    override fun getSupportDelegate(): SupportActivityDelegate {
        return mSupportActivityDelegate
    }

    /**
     * Perform some extra transactions.
     * 额外的事务：自定义Tag，添加SharedElement动画，操作非回退栈Fragment
     */
    override fun extraTransaction(): ExtraTransaction {
        return mSupportActivityDelegate.extraTransaction()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSupportActivityDelegate.onCreate(savedInstanceState)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        mSupportActivityDelegate.onPostCreate(savedInstanceState)
    }

    override fun onDestroy() {
        mSupportActivityDelegate.onDestroy()
        super.onDestroy()
    }

    /**
     * Note： return mSupportActivityDelegate.dispatchTouchEvent(ev) || super.dispatchTouchEvent(ev);
     */
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        return mSupportActivityDelegate.dispatchTouchEvent(ev) || super.dispatchTouchEvent(ev)
    }

    /**
     * 不建议复写该方法,请使用 [.onBackPressedSupport] 代替
     */
    override fun onBackPressed() {
        mSupportActivityDelegate.onBackPressed()
    }

    /**
     * 该方法回调时机为,Activity回退栈内Fragment的数量 小于等于1 时,默认finish Activity
     * 请尽量复写该方法,避免复写onBackPress(),以保证SupportFragment内的onBackPressedSupport()回退事件正常执行
     */
    override fun onBackPressedSupport() {
        mSupportActivityDelegate.onBackPressedSupport()
    }

    /**
     * 获取设置的全局动画 copy
     *
     * @return FragmentAnimator
     */
    override fun getFragmentAnimator(): FragmentAnimator {
        return mSupportActivityDelegate.fragmentAnimator
    }

    /**
     * Set all fragments animation.
     * 设置Fragment内的全局动画
     */
    override fun setFragmentAnimator(fragmentAnimator: FragmentAnimator) {
        mSupportActivityDelegate.fragmentAnimator = fragmentAnimator
    }

    /**
     * Set all fragments animation.
     * 构建Fragment转场动画
     *
     *
     * 如果是在Activity内实现,则构建的是Activity内所有Fragment的转场动画,
     * 如果是在Fragment内实现,则构建的是该Fragment的转场动画,此时优先级 > Activity的onCreateFragmentAnimator()
     *
     * @return FragmentAnimator对象
     */
    override fun onCreateFragmentAnimator(): FragmentAnimator {
        return mSupportActivityDelegate.onCreateFragmentAnimator()
    }

    /**
     * Causes the Runnable r to be added to the action queue.
     *
     *
     * The runnable will be run after all the previous action has been run.
     *
     *
     * 前面的事务全部执行后 执行该Action
     */
    override fun post(runnable: Runnable) {
        mSupportActivityDelegate.post(runnable)
    }

    /****************************************以下为可选方法(Optional methods) */

    // 选择性拓展其他方法

    fun loadRootFragment(containerId: Int, toFragment: ISupportFragment) {
        mSupportActivityDelegate.loadRootFragment(containerId, toFragment)
    }

    fun start(toFragment: ISupportFragment) {
        mSupportActivityDelegate.start(toFragment)
    }

    /**
     * @param launchMode Same as Activity's LaunchMode.
     */
    fun start(toFragment: ISupportFragment, @ISupportFragment.LaunchMode launchMode: Int) {
        mSupportActivityDelegate.start(toFragment, launchMode)
    }

    /**
     *
     * @see .popTo
     * @see .start
     */
    fun startWithPopTo(toFragment: ISupportFragment, targetFragmentClass: Class<*>, includeTargetFragment: Boolean) {
        mSupportActivityDelegate.startWithPopTo(toFragment, targetFragmentClass, includeTargetFragment)
    }

    /**
     * Pop the fragment.
     */
    fun pop() {
        mSupportActivityDelegate.pop()
    }

    /**
     * Pop the last fragment transition from the manager's fragment
     * back stack.
     */
    fun popTo(targetFragmentClass: Class<*>, includeTargetFragment: Boolean) {
        mSupportActivityDelegate.popTo(targetFragmentClass, includeTargetFragment)
    }

    /**
     * If you want to begin another FragmentTransaction immediately after popTo(), use this method.
     * 如果你想在出栈后, 立刻进行FragmentTransaction操作，请使用该方法
     */
    fun popTo(targetFragmentClass: Class<*>, includeTargetFragment: Boolean, afterPopTransactionRunnable: Runnable) {
        mSupportActivityDelegate.popTo(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable)
    }

    fun popTo(targetFragmentClass: Class<*>, includeTargetFragment: Boolean, afterPopTransactionRunnable: Runnable, popAnim: Int) {
        mSupportActivityDelegate.popTo(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable, popAnim)
    }

    /**
     * 获取栈内的fragment对象
     */
    fun <T : ISupportFragment> findFragment(fragmentClass: Class<T>): T {
        return SupportHelper.findFragment(supportFragmentManager, fragmentClass)
    }

}
