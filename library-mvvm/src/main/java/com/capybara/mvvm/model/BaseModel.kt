package com.capybara.mvvm.model

import com.capybara.mvvm.IModel
import com.capybara.mvvm.vm.base.BaseViewModel

open class BaseModel(viewModel: BaseViewModel<*>) : IModel {

    override fun onCleared() {
    }
}