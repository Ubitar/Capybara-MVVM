package com.ubitar.capybara.mvvm.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

import me.yokeyword.fragmentation.ExtraTransaction
import me.yokeyword.fragmentation.ISupportFragment
import me.yokeyword.fragmentation.SupportFragmentDelegate
import me.yokeyword.fragmentation.SupportHelper
import me.yokeyword.fragmentation.anim.FragmentAnimator

abstract class BaseSupportFragment : Fragment(), ISupportFragment {
    protected val mSupportFragmentDelegate = SupportFragmentDelegate(this)
    protected var _mActivity: FragmentActivity? = null

    override fun getSupportDelegate(): SupportFragmentDelegate {
        return mSupportFragmentDelegate
    }

    /**
     * Perform some extra transactions.
     * 额外的事务：自定义Tag，添加SharedElement动画，操作非回退栈Fragment
     */
    override fun extraTransaction(): ExtraTransaction {
        return mSupportFragmentDelegate.extraTransaction()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mSupportFragmentDelegate.onAttach()
        _mActivity = mSupportFragmentDelegate.activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSupportFragmentDelegate.onCreate(savedInstanceState)
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        return mSupportFragmentDelegate.onCreateAnimation(transit, enter, nextAnim)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mSupportFragmentDelegate.onActivityCreated(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mSupportFragmentDelegate.onSaveInstanceState(outState)
    }

    override fun onResume() {
        super.onResume()
        mSupportFragmentDelegate.onResume()
    }

    override fun onPause() {
        super.onPause()
        mSupportFragmentDelegate.onPause()
    }

    override fun onDestroyView() {
        mSupportFragmentDelegate.onDestroyView()
        super.onDestroyView()
    }

    override fun onDestroy() {
        mSupportFragmentDelegate.onDestroy()
        super.onDestroy()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        mSupportFragmentDelegate.onHiddenChanged(hidden)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        mSupportFragmentDelegate.setUserVisibleHint(isVisibleToUser)
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
        mSupportFragmentDelegate.post(runnable)
    }

    /**
     * Called when the enter-animation end.
     * 入栈动画 结束时,回调
     */
    override fun onEnterAnimationEnd(savedInstanceState: Bundle?) {
        mSupportFragmentDelegate.onEnterAnimationEnd(savedInstanceState)
    }


    /**
     * Lazy initial，Called when fragment is first called.
     *
     *
     * 同级下的 懒加载 ＋ ViewPager下的懒加载  的结合回调方法
     */
    override fun onLazyInitView(savedInstanceState: Bundle?) {
        mSupportFragmentDelegate.onLazyInitView(savedInstanceState)
    }

    /**
     * Called when the fragment is visible.
     * 当Fragment对用户可见时回调
     *
     *
     * Is the combination of  [onHiddenChanged() + onSupportVisible()/onSupportInvisible() + setUserVisibleHint()]
     */
    override fun onSupportVisible() {
        mSupportFragmentDelegate.onSupportVisible()
    }

    /**
     * Called when the fragment is invivible.
     *
     *
     * Is the combination of  [onHiddenChanged() + onSupportVisible()/onSupportInvisible() + setUserVisibleHint()]
     */
    override fun onSupportInvisible() {
        mSupportFragmentDelegate.onSupportInvisible()
    }

    /**
     * Return true if the fragment has been supportVisible.
     */
    override fun isSupportVisible(): Boolean {
        return mSupportFragmentDelegate.isSupportVisible
    }

    /**
     * Set fragment animation with a higher priority than the ISupportActivity
     * 设定当前Fragmemt动画,优先级比在SupportActivity里高
     */
    override fun onCreateFragmentAnimator(): FragmentAnimator {
        return mSupportFragmentDelegate.onCreateFragmentAnimator()
    }

    /**
     * 获取设置的全局动画 copy
     *
     * @return FragmentAnimator
     */
    override fun getFragmentAnimator(): FragmentAnimator {
        return mSupportFragmentDelegate.fragmentAnimator
    }

    /**
     * 设置Fragment内的全局动画
     */
    override fun setFragmentAnimator(fragmentAnimator: FragmentAnimator) {
        mSupportFragmentDelegate.fragmentAnimator = fragmentAnimator
    }

    /**
     * 按返回键触发,前提是SupportActivity的onBackPressed()方法能被调用
     *
     * @return false则继续向上传递, true则消费掉该事件
     */
    override fun onBackPressedSupport(): Boolean {
        return mSupportFragmentDelegate.onBackPressedSupport()
    }

    /**
     * 类似 [Activity.setResult]
     *
     *
     * Similar to [Activity.setResult]
     *
     * @see .startForResult
     */
    override fun setFragmentResult(resultCode: Int, bundle: Bundle) {
        mSupportFragmentDelegate.setFragmentResult(resultCode, bundle)
    }

    /**
     * @see .startForResult
     */
    override fun onFragmentResult(requestCode: Int, resultCode: Int, data: Bundle) {
        mSupportFragmentDelegate.onFragmentResult(requestCode, resultCode, data)
    }

    /**
     * 在start(TargetFragment,LaunchMode)时,启动模式为SingleTask/SingleTop, 回调TargetFragment的该方法
     * @param args putNewBundle(Bundle newBundle)
     * @see .start
     */
    override fun onNewBundle(args: Bundle) {
        mSupportFragmentDelegate.onNewBundle(args)
    }

    /**
     * 添加NewBundle,用于启动模式为SingleTask/SingleTop时
     *
     * @see .start
     */
    override fun putNewBundle(newBundle: Bundle) {
        mSupportFragmentDelegate.putNewBundle(newBundle)
    }


    /****************************************以下为可选方法(Optional methods) */
    // 自定制Support时，可移除不必要的方法

    /**
     * 隐藏软键盘
     */
    protected fun hideSoftInput() {
        mSupportFragmentDelegate.hideSoftInput()
    }

    /**
     * 显示软键盘,调用该方法后,会在onPause时自动隐藏软键盘
     */
    protected fun showSoftInput(view: View) {
        mSupportFragmentDelegate.showSoftInput(view)
    }

    /**
     * 加载根Fragment, 即Activity内的第一个Fragment 或 Fragment内的第一个子Fragment
     *
     * @param containerId 容器id
     * @param toFragment  目标Fragment
     */
    fun loadRootFragment(containerId: Int, toFragment: ISupportFragment) {
        mSupportFragmentDelegate.loadRootFragment(containerId, toFragment)
    }

    fun loadRootFragment(containerId: Int, toFragment: ISupportFragment, addToBackStack: Boolean, allowAnim: Boolean) {
        mSupportFragmentDelegate.loadRootFragment(containerId, toFragment, addToBackStack, allowAnim)
    }

    fun start(toFragment: ISupportFragment) {
        mSupportFragmentDelegate.start(toFragment)
    }

    /**
     * @param launchMode Similar to Activity's LaunchMode.
     */
    fun start(toFragment: ISupportFragment, @ISupportFragment.LaunchMode launchMode: Int) {
        mSupportFragmentDelegate.start(toFragment, launchMode)
    }

    /**
     * Launch an fragment for which you would like a result when it poped.
     */
    fun startForResult(toFragment: ISupportFragment, requestCode: Int) {
        mSupportFragmentDelegate.startForResult(toFragment, requestCode)
    }

    /**
     * Start the target Fragment and pop itself
     */
    fun startWithPop(toFragment: ISupportFragment) {
        mSupportFragmentDelegate.startWithPop(toFragment)
    }

    /**
     * @see .popTo
     * @see .start
     */
    fun startWithPopTo(toFragment: ISupportFragment, targetFragmentClass: Class<*>, includeTargetFragment: Boolean) {
        mSupportFragmentDelegate.startWithPopTo(toFragment, targetFragmentClass, includeTargetFragment)
    }

    fun replaceFragment(toFragment: ISupportFragment, addToBackStack: Boolean) {
        mSupportFragmentDelegate.replaceFragment(toFragment, addToBackStack)
    }

    fun pop() {
        mSupportFragmentDelegate.pop()
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
        mSupportFragmentDelegate.popTo(targetFragmentClass, includeTargetFragment)
    }

    /**
     * 获取栈内的fragment对象
     */
    fun <T : ISupportFragment> findChildFragment(fragmentClass: Class<T>): T {
        return SupportHelper.findFragment(childFragmentManager, fragmentClass)
    }
}