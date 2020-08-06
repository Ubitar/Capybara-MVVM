package com.example.example.demo1

import android.app.Application
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.capybara.mvvm.action.ActivityActions
import com.capybara.mvvm.model.BaseModel
import com.capybara.mvvm.vm.base.BaseActivityViewModel

class Demo1ViewModel(application: Application) : BaseActivityViewModel<BaseModel>(application) {

    val count = MutableLiveData<Int>(0)

    /** 这个是 MVVM 中的  Model层，如没有网络或数据库需求，传NUll即可 */
    override fun getModel(): Class<BaseModel>? = null

    /** 创建自己的业务的Actions 并转达给父类，Actions用于与Activity交流，此处传NUll即可*/
    override fun onCreateActions(): ActivityActions? =null

    //用于初始化RecyclerView adapter的事件监听
    override fun initEvent() {
        super.initEvent()
    }

    //初始化数据或者获取数据
    override fun initData() {
        super.initData()
    }

    fun onClickBtn1(view: View) {
        count.value = count.value!! + 1
    }

}