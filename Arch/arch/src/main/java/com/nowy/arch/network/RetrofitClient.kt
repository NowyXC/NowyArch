package com.nowy.arch.network

import android.util.Log
import com.blankj.utilcode.util.LogUtils
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 *
 * @ClassName:      RetrofitClient
 * @Description:    作用描述
 * @Author:         Nowy
 * @CreateDate:     2020/7/23 18:27
 * @UpdateUser:
 * @UpdateDate:     2020/7/23 18:27
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class RetrofitClient(BASE_URL :String) {
    companion object{
        var DEBUG = true
        var TAG :String  = "arch::net"
        const val DEFAULT_READ_TIMEOUT_MILLIS = 15L
        const val DEFAULT_WRITE_TIMEOUT_MILLIS = 20L
        const val DEFAULT_CONNECT_TIMEOUT_MILLIS = 20L
//        const val HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 10 * 1024 * 1024.toLong()

//        fun getInstance() = SingletonHolder.INSTANCE
        fun config(isDebug:Boolean,tag:String = TAG){
            DEBUG = isDebug
            TAG = tag
        }
    }

//    private object SingletonHolder {
//        val INSTANCE = RetrofitClient()
//    }

    private var retrofit: Retrofit


    init {
        retrofit = Retrofit.Builder()
            .client(getOkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory())
            .baseUrl(BASE_URL)
            .build()
    }


    fun <T> createService(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }


    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(DEFAULT_CONNECT_TIMEOUT_MILLIS, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_READ_TIMEOUT_MILLIS,TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_WRITE_TIMEOUT_MILLIS, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor(getLogger())
                .setLevel(
                    if(DEBUG){
                        HttpLoggingInterceptor.Level.BODY
                    }else{
                        HttpLoggingInterceptor.Level.NONE
                    }
                ))
            .connectionPool(ConnectionPool(8, 15, TimeUnit.SECONDS))
            .build()
    }

    private fun getLogger():HttpLoggingInterceptor.Logger{
        return object :HttpLoggingInterceptor.Logger{
            override fun log(message: String) {
                try {
                    Log.i(TAG,message)
                }catch (e:Exception){
                    e.printStackTrace()
                    Log.e(TAG,message)
                }
            }
        }
    }
}