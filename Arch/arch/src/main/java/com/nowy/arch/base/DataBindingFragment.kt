package com.nowy.arch.base

import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.LogUtils

/**
 *
 * @ClassName:      DataBindingFragment
 * @Description:    启用dataBinding的抽象类
 *
 *  通过 activityViewModels() 获取基于activity的viewModel
 *  viewModels() 获取基于当前fragment的viewModel
 *
 * @Author:         Nowy
 * @CreateDate:     2020/7/23 17:37
 * @UpdateUser:
 * @UpdateDate:     2020/7/23 17:37
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
abstract class DataBindingFragment<Binding : ViewDataBinding>:BasicFragment() {

    protected lateinit var binding: Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = generateBinding(inflater, container)
        return binding.root
    }

    /**
     * 生成ViewDataBinding方法
     */
    protected open fun generateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?):Binding{

        val dataBindingConfig: DataBindingConfig = getDataBindingConfig()
        val binding: Binding =
            DataBindingUtil.inflate(inflater, dataBindingConfig.layoutRes, container, false)
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