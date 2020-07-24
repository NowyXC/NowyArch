package com.nowy.arch.base

import android.os.Bundle
import android.os.Message
import androidx.activity.ComponentActivity
import androidx.annotation.LayoutRes
import androidx.annotation.MainThread
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider

/**
 *
 * @ClassName:      DataBindingActivity
 * @Description:    启用dataBinding的抽象类
 *
 * viewModels() 获取基于当前Activity的viewModel
 *
 * @Author:         Nowy
 * @CreateDate:     2020/7/23 11:34
 * @UpdateUser:
 * @UpdateDate:     2020/7/23 11:34
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
abstract class DataBindingActivity<Binding : ViewDataBinding>:BasicActivity() {

    protected lateinit var binding: Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = generateBinding()

    }


    /**
     * 生成ViewDataBinding方法
     */
    protected open fun generateBinding():Binding{
        val dataBindingConfig: DataBindingConfig = getDataBindingConfig()
        val binding: Binding =
            DataBindingUtil.setContentView(this, dataBindingConfig.layoutRes)
        binding.lifecycleOwner = this
        val bindingParams = dataBindingConfig.bindingParams
        bindingParams.forEach{
            binding.setVariable(it.key, it.value)
            if(it.value is BaseViewModel){ //检测到BaseViewModel则绑定默认的UI事件
                registerDefUIEvent(it.value as BaseViewModel)
            }
        }
        return binding
    }


    protected abstract fun getDataBindingConfig(): DataBindingConfig

    /**
     * 注册 UI 事件
     */
    open fun registerDefUIEvent(viewModel: BaseViewModel) {
        viewModel.let {vm->
            if(vm.defUIEvent.isBound) return
            vm.defUIEvent.isLoading.observe(this, Observer {
                if(it != null && it){
                    showLoading()
                }else{
                    dismissLoading()
                }
            })
            vm.defUIEvent.shortToast.observe(this, Observer {
                showShortToast(it)
            })
            vm.defUIEvent.longToast.observe(this, Observer {
                showLongToast(it)
            })
            vm.defUIEvent.msgEvent.observe(this, Observer {
                handleEvent(it)
            })
            vm.defUIEvent.isBound = true
        }
    }


    open fun handleEvent(msg: Message?) {}



    @MainThread
    inline fun <reified VM : ViewModel> ComponentActivity.viewModels(
        noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
    ): Lazy<VM> {
        val factoryPromise = factoryProducer ?: {
            defaultViewModelProviderFactory
        }

        return ViewModelLazy(VM::class, { viewModelStore }, factoryPromise)
    }

    /**
     * 通过dataBinding绑定界面
     */
    protected inline fun <reified T : ViewDataBinding> binding(
        @LayoutRes resId: Int
    ): Lazy<T> = lazy {
        DataBindingUtil.setContentView<T>(this, resId)
    }

    inner class DataBindingConfig(val layoutRes:Int) {

        val bindingParams = HashMap<Int,Any>()

        fun addBindingParam(variableId: Int, any: Any): DataBindingConfig {
            if (bindingParams[variableId] == null) {
                bindingParams[variableId] = any
            }
            return this
        }
    }
}