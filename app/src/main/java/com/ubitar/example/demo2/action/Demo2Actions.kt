package com.ubitar.example.demo2.action

import com.ubitar.capybara.mvvm.action.ActivityActions
import com.ubitar.capybara.mvvm.action.SingleLiveAction

class Demo2Actions : ActivityActions() {

    val customAction: CustomAction by lazy { CustomAction() }

    class CustomAction: SingleLiveAction<Int>() {
        override fun describe(): String {
            return "自定义的Action，可以订阅这个Action实现ViewModel向Activity或者Fragment等进行单向传递消息，" +
                    "注意，我这里传的是一个Int格式，有需要可以换成你要的其他格式，比如bean类，这ViewModel和View的交流也只有这种办法了"
        }
    }
}