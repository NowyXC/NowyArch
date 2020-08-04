package com.nowy.archsample

import androidx.lifecycle.*
import com.blankj.utilcode.util.LogUtils
import com.nowy.arch.base.BaseViewModel
import com.nowy.archsample.model.AicCloudServerConfigVO
import com.nowy.archsample.network.TestApiClient
import com.nowy.archsample.repository.TestRepository
import com.skydoves.sandwich.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
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


    fun testAsync(){
        launchUI {
            //main 线程
            LogUtils.e("nowy","async 当前线程::${Thread.currentThread().name} start")
            // async 是并行执行的

            val req01 = async(Dispatchers.IO){
                LogUtils.e("nowy","async01 当前线程::${Thread.currentThread().name} start")
                TestApiClient.getInstance().fetchServerConfig()
            }

            val req02 = async(Dispatchers.IO){ TestApiClient.getInstance().fetchServerConfig() }

            //等待结果，阻塞
            val resp01 = req01.await()

            //main 线程
            resp01.onSuccess {
                LogUtils.e("nowy","resp01 当前线程::${Thread.currentThread().name} complete")
            }

            val resp02 = req02.await()

            //main 线程
            resp02.onSuccess {
                LogUtils.e("nowy","resp02 当前线程::${Thread.currentThread().name} complete")
            }
            LogUtils.e("nowy","async 当前线程::${Thread.currentThread().name} end")
        }
    }

    fun testWithContext(){
        launchUI {

            LogUtils.e("nowy","withContext 当前线程::${Thread.currentThread().name} start")
            // withContext 串行执行

            val resp01 = withContext(Dispatchers.IO){
                LogUtils.e("nowy","withContext01 当前线程::${Thread.currentThread().name} start")
                TestApiClient.getInstance().fetchServerConfig()
            }

            resp01.onSuccess {
                //doSomeThing
                LogUtils.e("nowy","resp01 当前线程::${Thread.currentThread().name} doSomeThing")
            }

            val resp02 = withContext(Dispatchers.IO){ TestApiClient.getInstance().fetchServerConfig() }
            resp02.onSuccess {
                //doSomeThing
                LogUtils.e("nowy","resp02 当前线程::${Thread.currentThread().name} doSomeThing")
            }

            LogUtils.e("nowy","withContext 当前线程::${Thread.currentThread().name} end")
        }
    }

}