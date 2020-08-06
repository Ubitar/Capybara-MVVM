## 一个基于Android MVVM的架构🐖

以下文章，我会把本架构称为 **Capybara**

放下Github链接先[https://github.com/Ubitar/MVVMCapybara](https://github.com/Ubitar/MVVMCapybara)


Capybara 使用`databinding + fragmentation`搭建，仅包含`Activity`及`Fragment`等组件基础功能，
可能有些人喜欢用 `navigation`，架构有分层，大家可以自己fork下来删减定制。
<br/>
- ###### 前言<br/>
- ###### 结构简述<br/>
- ###### 食用方法<br/>
- ###### 常见问题<br/><br/>

## 前言

在阅读 Capybara 前，我会默认大家都看过`databinding、fragmentation、LiveData`的使用方法，及kotlin的使用，kt真香。

不过我希望先阅读一下下面链接里的大佬文章，加深对MVVM和数据驱动的了解。
[https://www.zhihu.com/question/30976423/answer/106134677](https://www.zhihu.com/question/30976423/answer/106134677)
[https://www.jianshu.com/p/1fcda521fcda](https://www.jianshu.com/p/1fcda521fcda)  禁止在layout中写复杂逻辑
>当然，如果你做过Vue或者微信小程序那更好理解了，即使那时你会嫌弃安卓的MVVM，或者充满黑人问号。

## 结构简述
Capybara 主要通过让组件继承` IView 、IViewModel 、IModel `这3个接口来实现的。

![结构](https://upload-images.jianshu.io/upload_images/15368523-062689e0bbe05fa5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**View** 层可以为你的Activity、Fragment或者DialogFragment
**Model** 层为为你的业务提供网络请求服务或者数据库读存服务
**ViewModel** **中间层** 则是负责处理你的业务逻辑，从Model中获取数据进行处理，并对View进行更新的

图中`BaseMvvMActivity`（我写错成了BseMvvMActivtivty了）、`BaseViewModel`和`BaseModel`为MVVM的实现抽象类，在不同的生命周期中实现并调用了MVVM接口。
而`BaseMvvMFragment`也类似有同样的实现，只不过因其生命周期，调用方法的位置有些不同，具体内容需要大家去浏览源码。

如果你想支持例如` Popup` 之类的组件，也可以了解架构的大体走向后通过实现上方所提及的3个接口进行实现，当然，前提是你的这类`popup`组件得有一个说得过去的生命周期。
## 食用方法
###### 1、新建一个布局文件，里面就只有一个按钮
```
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">
    <data>
        <variable
            name="viewModelName"
            type="com.example.example.demo1.Demo1ViewModel" />
    </data>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:animateLayoutChanges="true"
        android:orientation="vertical">
        <Button
            android:id="@+id/btn1"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text='@{"点击这个按钮  "+viewModelName.count}'
            android:textAllCaps="false"
            app:onClick="@{viewModelName.onClickBtn1}"
            tools:text="点击这个按钮" />
    </LinearLayout>
</layout>
```
###### 2、新建一个`ViewModel`，继承自`BaseActivityViewModel`
**注意：**这个ViewModel是继承自ActivityViewModel的，因为我们接下来要创建的是其相应的`Activity`，如果你要创建的是`Fragment`,那么你需要继承的则是`BaseFragmentViewModel`，同理，`Dialog`是`BaseDialogViewModel`
```
class Demo1ViewModel(application: Application) : BaseActivityViewModel<BaseModel>(application) {

    val count = MutableLiveData<Int>(0)

    /** 这个是 MVVM 中的  Model层，如没有网络或数据库需求，传NUll即可 */
    override fun getModel(): Class<BaseModel>? = null

    /** 创建自己的业务的Actions 并转达给父类，Actions用于与Activity交流，后面会讲 ，此处传NUll即可*/
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
```
###### 3、新建你的`Activity` ，继承 `BaseActivity`并实现`getLayoutId()`和`getViewModelId()`提供布局Id和ViewModel在布局中的变量名称
```
class Demo1Activity: BaseActivity<ActivityDemo1Binding, Demo1ViewModel>() {

    /** 你的布局Id **/
    override fun getLayoutId(inflater: LayoutInflater, savedInstanceState: Bundle?): Int = R.layout.activity_demo1

    /** 布局中ViewModel的Name  */
    override fun getViewModelId(): Int =BR.viewModelName

    /**
     * 下方函数运行顺序（从上往下顺序运行）
     *
     *  initParams()
     *  onCreatedViewModel()  /   initDaggerInject()
     *  onBeforeObservable()
     *  onBindObservable()
     *  initViewModelParams()
     *  initView()
     *  ViewModel.initEvent()
     *  ViewModel.initData()
     *
     */
    //用于接收并处理从上一个界面传递过来的数据
    override fun initParams() {
        super.initParams()
    }

    override fun onCreatedViewModel() {
        super.onCreatedViewModel()
    }

    //这里可以进行Dagger注入
    override fun initDaggerInject() {
        super.initDaggerInject()
    }

    override fun onBeforeObservable() {
        super.onBeforeObservable()
    }

    //如果有需要，可以通过这个函数把初始数据传到ViewModel
    override fun initViewModelParams() {
        super.initViewModelParams()
        viewModel.count.value = 100
    }

    // 初始化RecyclerView或者其他View
    override fun initView() {
        super.initView()
    }

    // 注册ViewModel中变量值的改变，可用于ViewModel向View传递信息或操作
    override fun onBindObservable() {
        super.onBindObservable()
    }
}
```
###### 4、或许你的项目里会有读取数据库或者网络的时候，那么你就需要实现相应的`Model`层，编写一个`Model`层文件
```
class Demo3Model(
    private val viewModel: Demo3ViewModel
) : BaseModel(viewModel) {

    fun toLogin(account: String, password: String) {
        //模拟登录
        Flowable.timer(1000, TimeUnit.MILLISECONDS)
            .doOnSubscribe { ToastUtils.showShort("加载中") }
                //这个是AutoDispose  如果要Rxlifecycle你要去想办法自己更换
//            .`as`(AutoDisposeUtil.fromOnDestroy(viewModel.lifecycle.get()!!))
            .subscribe({
                viewModel.afterLoginSuccess()
            }, {
                println(it)
            }).isDisposed
    }

    override fun onCleared() {
        super.onCleared()
    }
}
```
在ViewModel处编写`getModel()`函数的返回值，同时别忘了修改类上方的泛型
```
class Demo3ViewModel(application: Application) : BaseActivityViewModel<Demo3Model>(application) {

    override fun getModel(): Class<Demo3Model>? = Demo3Model::class.java

    /** 创建自己的业务的Demo1Actions ，并转达给父类 ，若无需Actions，传NUll即可*/
    override fun onCreateActions(): ActivityActions? =null

    var account = MutableLiveData<String>("")
    var password = MutableLiveData<String>("")

    fun onClickLogin(view: View) {
        model.toLogin(account.value ?: "", password.value ?: "")
    }

    fun afterLoginSuccess() {
        ToastUtils.showShort("登陆成功")
    }

}
```
###### 5、Actions的使用：`ViewModel`如何和`View(Activity）`通信
既然前面有说过`ViewModel`不持有`View`的对象，那`ViewModel怎`么告诉`View`执行一些UI上的事呢，来看下下图。![ViewModel通过Actions给View发送消息](https://upload-images.jianshu.io/upload_images/15368523-78fcc0de7f95efa2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)`ViewModel`通过建立一个专属的Actions行为集合类，由`View`负责监听，`ViewModel`负责发送的方式传递行为意图，`View`接收后做出响应。举个例子：让`View`显示一个`Toast`
（1）**创建Actions类**：建立该`ViewModel`的`Actions`（行为集合），该`Actions`继承自`ActivityActions`，`FragmentViewModel`对应的是`FragmentActions`。`Actions`集合中的`CustomAction`是本次交流的行为类型，是承载消息的主体。
```
class Demo2Actions : ActivityActions() {

    val customAction: CustomAction by lazy { CustomAction() }

    class CustomAction: SingleLiveAction<Int>() {
        override fun describe(): String {
            return "自定义的Action，可以订阅这个Action实现ViewModel向Activity或者Fragment等进行单向传递消息，" +
                    "注意，我这里传的是一个Int格式，有需要可以换成你要的其他格式，比如bean类，这ViewModel和View的交流也只有这种办法了"
        }
    }
}
```
（2）**构建Actions对象**：在其`ViewModel`中创建`Actions`的对象，通过`onCreateActions`传给父类（`ViewModel`父类封装的部分通用功能需要`Actions`才能使用）
```
class Demo2ViewModel(application: Application) : BaseActivityViewModel<BaseModel>(application) {

    //@Inject  如果是用了Dagger可以通过注入赋值
    val actions = Demo2Actions()

    /** 这个是 MVVM 中的  Model层，如没有网络或数据库需求，传NUll即可 */
    override fun getModel(): Class<BaseModel>? = null

    /** 创建自己的业务的Demo1Actions ，并转达给父类 ，若无需Actions，传NUll即可*/
    override fun onCreateActions(): ActivityActions? =actions

    fun onClickBtn5(view: View) {
        actions2.customAction.call(100)
    }
}
```
（3）在`View`监听`ViewModel`中`Actions`发送的行为消息：
```
class Demo2Activity: BaseActivity<ActivityDemo2Binding, Demo2ViewModel>() {
    override fun getLayoutId(inflater: LayoutInflater, savedInstanceState: Bundle?): Int = R.layout.activity_demo2

    override fun getViewModelId(): Int =BR.viewModel

    override fun onBindObservable() {
        super.onBindObservable()
        viewModel.actions.customAction.observe(this, Observer {
            ToastUtils.showShort("Activity收到了来自ViewModel的信息 $it")
        })
    }
}
```
（4）从`ViewModel`中调用发送行为消息语句：发送时夹带参数值100
```
    fun onClickBtn5(view: View) {
        actions.customAction.call(100)
    }
```
![](https://upload-images.jianshu.io/upload_images/15368523-2313e46b3a2693be.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
<br/>
###### 6、关于`fragmentation`在这个架构上的应用可以查看源码[demo4](https://github.com/Ubitar/MVVMCapybara/tree/master/app/src/main/java/com/example/example/demo4)



## 常见问题
###### 1、这架构胶水代码好多
额。。。。这个我也想解决，但是奈何我还不会写模板一键生成
###### 2、MVVM好用不
这个问题就很刁钻了，MVVM和MVP一个自底向上，一个自顶向下，写法完全相反，而且MVVM并不是android原生就有的，稍微复杂点的逻辑不能写在xml（或者说太多if条件），xml报错的时候也一脸懵逼，只能一个一个注释排查，你想从不是MVVM架构的项目中复制代码？对不起，大部分你都要重写。
但是好处还是有的，感触最深的还是改了只改变一个变量就显示隐藏了多个View，其次是`@BindingAdapter`对UI操作的封装，其他的还要自己去体会。

[大哥，觉得可以的话Gayhub给个Star吧，他们是有交互效果的啊](https://github.com/Ubitar/MVVMCapybara)

![知道这是啥不](https://upload-images.jianshu.io/upload_images/15368523-4265dfab708ba32b.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)