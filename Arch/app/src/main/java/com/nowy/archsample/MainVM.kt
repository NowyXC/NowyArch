package com.nowy.archsample

import androidx.lifecycle.*
import com.nowy.arch.base.BaseViewModel
import com.nowy.archsample.model.AicCloudServerConfigVO
import com.nowy.archsample.network.TestApiClient
import com.nowy.archsample.repository.TestRepository

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
    }

    fun test2(){
        launchUI(){
            mainServerConfigLiveData.value = testRepository.fetchServerConfig2()
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}