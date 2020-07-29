package com.nowy.arch.base

import android.os.Message
import androidx.lifecycle.*
import com.blankj.utilcode.util.Utils
import com.nowy.arch.livedata.SingleLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

/**
 *
 * @ClassName:      BaseViewModel
 * @Description:
 *
 * 网络请求调用示例
 *
 *
 * val toastLiveData: MutableLiveData<String> = MutableLiveData()
 * val isLoading: ObservableBoolean = ObservableBoolean(false)
 *
 * launchOnViewModelScope {
 *      this.mainRepository.fetchPokemonList(
 *          page = it,
 *          onSuccess = { isLoading.set(false) },
 *          onError = { toastLiveData.postValue(it) }
 *      ).asLiveData()
 *  }
 *
 *
 *
 * 数据驱动模式：
 *
 * val pokemonListLiveData: LiveData<List<Pokemon>>
 * val toastLiveData: MutableLiveData<String> = MutableLiveData()
 * val isLoading: ObservableBoolean = ObservableBoolean(false)
 *
 *
 * pokemonListLiveData = pokemonFetchingLiveData.switchMap {
 *      isLoading.set(true)
 *      launchOnViewModelScope {
 *          this.mainRepository.fetchPokemonList(
 *              page = it,
 *              onSuccess = { isLoading.set(false) },
 *              onError = { toastLiveData.postValue(it) }
 *          ).asLiveData()
 *      }
 *  }
 *
 * @Author:         Nowy
 * @CreateDate:     2020/7/23 15:09
 * @UpdateUser:
 * @UpdateDate:     2020/7/23 15:09
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
open class BaseViewModel:AndroidViewModel(Utils.getApp()) {
    val defUIEvent: DefUIEvent by lazy { DefUIEvent() }


    /**
     * 将viewModel作用域和IO作用域协程转为LiveData
     * flow{...}.asLiveData()
     */
    inline fun <T> launchOnViewModelScope(crossinline block: suspend () -> LiveData<T>): LiveData<T> {
        return liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emitSource(block())
        }
    }

    /**
     * 所有网络请求都在 viewModelScope 域中启动，当页面销毁时会自动
     * 调用ViewModel的  #onCleared 方法取消所有协程
     */
    inline fun launchUI(crossinline block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch { block() }

    /**
     * 用流的方式进行网络请求
     */
    fun <T> launchFlow(block: suspend () -> T): Flow<T> {
        return flow {
            emit(block())
        }
    }


    inner class DefUIEvent {
        val isLoading by lazy { SingleLiveData<Boolean>() }
        val shortToast by lazy { SingleLiveData<String>() }
        val longToast by lazy { SingleLiveData<String>() }
        val msgEvent by lazy { SingleLiveData<Message>() }
        //用于判断已经绑定UI的ViewModel，例如activity在fragment中使用
        var isBound = false
    }
}