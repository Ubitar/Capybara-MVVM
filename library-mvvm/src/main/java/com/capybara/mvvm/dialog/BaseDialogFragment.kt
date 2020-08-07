package com.capybara.mvvm.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.databinding.ViewDataBinding
import com.capybara.mvvm.vm.base.BaseDialogViewModel

abstract class BaseDialogFragment<V : ViewDataBinding, VM : BaseDialogViewModel<*>> :
    BaseMvvMDialogFragment<V, VM>() {

    private var onCanceledListener: (() -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root= super.onCreateView(inflater, container, savedInstanceState)
        (root as LinearLayout).gravity=getGravity()
        root.setOnClickListener {
            if (getOutsideEnable()) {
                onCanceledListener?.invoke()
                dismissAllowingStateLoss()
            }
        }
        return root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        if (!isDimAmountEnable())
            dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog.window?.setDimAmount(getDimAmount())
        dialog.setOnKeyListener { dialogInterface, i, keyEvent ->
            if (getBackEnable() && i == KeyEvent.KEYCODE_BACK && keyEvent.action == KeyEvent.ACTION_DOWN) {
                onCanceledListener?.invoke()
            }
            return@setOnKeyListener !getBackEnable() && i == KeyEvent.KEYCODE_BACK
        }
        val dialogWindow = dialog.window
        dialog.window?.decorView?.setPadding(0, 0, 0, 0)
        dialogWindow?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        return dialog
    }


    override fun onCreatedViewModel() {
        super.onCreatedViewModel()
        initDaggerInject()
    }

    override fun onBeforeObservable() {
        super.onBeforeObservable()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModelParams()
        initView()
        viewModel.initEvent()
        viewModel.initData()
    }

    fun setOnCanceledListener(listener: (() -> Unit)?): BaseDialogFragment<*, *> {
        this.onCanceledListener = listener
        return this
    }

    private fun getRootView(decorView: ViewGroup): View {
        return (decorView.findViewById(android.R.id.content) as ViewGroup).getChildAt(0)
    }

    open fun getGravity(): Int {
        return Gravity.CENTER
    }

    /** 是否能够点击空白处关闭弹窗 */
    open fun getOutsideEnable(): Boolean {
        return true
    }

    /** 是否能够点击返回关闭弹窗 */
    open fun getBackEnable(): Boolean {
        return true
    }

    /** 是否显示遮罩 */
    open fun isDimAmountEnable(): Boolean {
        return true
    }

    /** 设置遮罩效果 */
    open fun getDimAmount(): Float {
        return 0.5f
    }

    /** 初始化页面参数  */
    open fun initParams() {

    }

    /** Dagger注入 */
    open fun initDaggerInject(){

    }

    /** 初始化或传递ViewModel的参数  */
    open fun initViewModelParams() {

    }

    /** 初始化视图  */
    open fun initView() {

    }

}