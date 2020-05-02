package com.example.example.demo2

import android.app.Application
import android.text.Editable
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.ToastUtils
import com.capybara.mvvm.model.BaseModel
import com.capybara.mvvm.vm.base.BaseActivityViewModel
import com.example.example.demo2.action.Demo2Actions

class Demo2ViewModel(application: Application) : BaseActivityViewModel<BaseModel>(application) {

    var valueFromActivity = "这个值从Activity那进行设置注入"

    val edtContent1 = MutableLiveData("")
    val checkBoxValue = MutableLiveData(false)
    val checkBoxText = MutableLiveData("选择后显示下方按钮")
    val radioBtnValue = MutableLiveData(false)

    val actions2 = Demo2Actions()

    /** 这个是 MVVM 中的  Model层，如没有网络或数据库需求，传NUll即可 */
    override fun getModel(): Class<BaseModel>? = null

    fun onClickBtn1(view: View) {
        finish()
    }

    fun onClickBtn2(view: View) {
        onBackPressedSupport()
    }

    fun onClickBtn3(view: View) {
        edtContent1.value = ""
    }

    fun onClickBtn4(view: View) {
        ToastUtils.showShort(edtContent1.value)
    }

    fun onClickBtn5(view: View) {
        actions2.customAction.call(100)
    }

    fun onClickCheckBox(view: View) {
        if (checkBoxValue.value!!) {
            checkBoxText.value = "取消选择后隐藏下方按钮"
        } else checkBoxText.value = "选择后显示下方按钮"
    }

    val onEditTextChangeListener = object : SimpleTextChangeListener() {
        override fun afterTextChanged(s: Editable) {
            println(s.toString())
        }
    }

}