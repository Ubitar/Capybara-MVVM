package com.capybara.mvvm.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.Gravity
import android.view.KeyEvent
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.ViewDataBinding
import com.capybara.mvvm.vm.base.BaseDialogViewModel

abstract class BaseDialogFragment<V : ViewDataBinding, VM : BaseDialogViewModel<*>> :
    BaseMvvMDialogFragment<V, VM>() {

    private var onDismissListener: (() -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initParams()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        if (!isDimAmountEnable())
            dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog.setOnKeyListener { dialogInterface, i, keyEvent ->
            return@setOnKeyListener !getBackEnable() && i == KeyEvent.KEYCODE_BACK
        }
        val dialogWindow = dialog.window
        dialogWindow?.setBackgroundDrawableResource(android.R.color.transparent)
        dialogWindow?.setGravity(getGravity())
        dialogWindow?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        (dialogWindow?.decorView as ViewGroup).getChildAt(0).setOnClickListener {
            if(getOutsideEnable()) dismiss()
        }
        return dialog
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModelParams()
        initView()
        viewModel.initEvent()
        viewModel.initData()
    }


    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismissListener?.invoke()
    }

    fun setOnDismissListener(listener: (() -> Unit)?): BaseDialogFragment<*, *> {
        this.onDismissListener = listener
        return this
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

    /** 初始化或传递ViewModel的参数  */
    open fun initViewModelParams() {

    }

    /** 初始化视图  */
    open fun initView() {

    }

}