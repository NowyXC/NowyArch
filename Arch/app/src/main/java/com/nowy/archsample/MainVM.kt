package com.nowy.archsample

import androidx.lifecycle.*
import com.nowy.arch.base.BaseViewModel
import com.nowy.archsample.model.AicCloudServerConfigVO
import com.nowy.archsample.network.TestApiClient
import com.nowy.archsample.repository.TestRepository
import com.skydoves.sandwich.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import okhttp3.internal.wait

/**
 *
 * @ClassName:      MainVM
 * @Description:    作用描述
 * @Author:         Nowy
 * @CreateDate:     2020/7/29 15:52
 * @UpdateUser:
 * @UpdateDate:     2020/7/29 15:52
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class MainVM:BaseViewModel() {
    private val testRepository : TestRepository = TestRepository(TestApiClient.getInstance())

    private val mainUILiveData: LiveData<String> = MutableLiveData()
    private var mainServerConfigLiveData  = MutableLiveData<AicCloudServerConfigVO?> ()
    private val serverConfigLiveData: LiveData<AicCloudServerConfigVO?> //MediatorLiveData

    init {
        serverConfigLiveData = mainUILiveData.switchMap {
            fetchServerConfig()
        }

    }

    //TODO 网络请求的liveData怎么转化为UI的LiveData,这个衔接处理
    //TODO 目标 1. 配置UILiveData与网络请求的监听关系 2.网络请求action() -> UI更新
    //网络请求每次会new一个liveData
    fun fetchServerConfig() :LiveData<AicCloudServerConfigVO?>{
        return launchOnViewModelScope {
            testRepository.fetchServerConfig(
                onSuccess = {defUIEvent.shortToast.value = "success"},
                onError = { defUIEvent.shortToast.value = "error" }
            ).asLiveData()
        }
    }

    fun test(){
        launchUI(){
            testRepository.fetchServerConfig(
                onSuccess = {defUIEvent.shortToast.value = "success"},
                onError = { defUIEvent.shortToast.value = "error" }
            )
        }



       fetch(
            action = {
                TestApiClient.getInstance().fetchServerConfig()
            },
            success = {
                mainServerConfigLiveData.value = it.data
            }
        )
    }



    fun <T> fetch(
        action: suspend CoroutineScope.() -> ApiResponse<T>,
        success:suspend CoroutineScope.(ApiResponse.Success<T>) -> Unit,
        error: suspend CoroutineScope.(ApiResponse<T>) -> Unit = {
            defUIEvent.shortToast.value = "${it}"
        },
        complete: suspend CoroutineScope.() -> Unit = {
            if(showDialog) defUIEvent.isLoading.value = false
        },
        showDialog: Boolean = true
    ){
        if(showDialog) defUIEvent.isLoading.value = true
        fetchData(action,success,error, complete)
    }

    private fun <T> fetchData(
        action: suspend CoroutineScope.() -> ApiResponse<T>,
        success:suspend CoroutineScope.(ApiResponse.Success<T>) -> Unit,
        error: suspend CoroutineScope.(ApiResponse<T>) -> Unit = {},
        complete: suspend CoroutineScope.() -> Unit = {}
    ){
        launchUI {
            withContext(Dispatchers.IO){ action() }
                .suspendOnSuccess {
                    success(this)
                    complete()
                }
                .suspendOnError {
                    error(this)
                    complete()
                }
                .suspendOnException {
                    error(this)
                    complete()
                }
        }
    }




    override fun onCleared() {
        super.onCleared()
    }
}