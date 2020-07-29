package com.nowy.archsample.network

import com.nowy.arch.network.RetrofitClient
import com.nowy.archsample.model.AicCloudServerConfigVO
import com.skydoves.sandwich.ApiResponse

/**
 *
 * @ClassName:      TestApiClient
 * @Description:    作用描述
 * @Author:         Nowy
 * @CreateDate:     2020/7/29 16:15
 * @UpdateUser:
 * @UpdateDate:     2020/7/29 16:15
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class TestApiClient private constructor():TestApi{
    companion object{
        fun getInstance() = SingletonHolder.INSTANCE
    }

    private object SingletonHolder {
        val INSTANCE = TestApiClient()
    }

    private val api:TestApi = RetrofitClient("http://192.168.21.2/").createService(TestApi::class.java)

    override suspend fun fetchServerConfig(): ApiResponse<AicCloudServerConfigVO> {
        return api.fetchServerConfig()
    }


}