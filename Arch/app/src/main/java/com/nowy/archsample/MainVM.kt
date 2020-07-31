package com.nowy.archsample

import androidx.lifecycle.*
import com.blankj.utilcode.util.LogUtils
import com.nowy.arch.base.BaseViewModel
import com.nowy.archsample.model.AicCloudServerConfigVO
import com.nowy.archsample.network.TestApiClient
import com.nowy.archsample.repository.TestRepository
import com.skydoves.sandwich.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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



    fun test(){
       fetch(
            action = {
                TestApiClient.getInstance().fetchServerConfig()
            },
            success = {
                LogUtils.e("nowy","当前线程::${Thread.currentThread().name} success")
                mainServerConfigLiveData.value = it.data
            },
            error = {
                LogUtils.e("nowy","当前线程::${Thread.currentThread().name} error")
            },
            complete = {
                LogUtils.e("nowy","当前线程::${Thread.currentThread().name} complete")
            }
            ,
           showDialog = true
        )
    }



}