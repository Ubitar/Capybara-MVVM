package com.ubitar.capybara.mvvm.model

import com.ubitar.capybara.mvvm.IModel
import com.ubitar.capybara.mvvm.vm.base.BaseViewModel

open class BaseModel(viewModel: BaseViewModel<*>) : IModel {

    override fun onCleared() {
    }
}