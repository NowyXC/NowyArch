package com.nowy.arch.network

import com.nowy.arch.model.InfoTest
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

/**
 *
 * @ClassName:      MockTestService
 * @Description:
 *  测试接口类，一般直接使用项目的apiService
 *  此处只是提供示例
 * @Author:         Nowy
 * @CreateDate:     2020/7/24 17:02
 * @UpdateUser:
 * @UpdateDate:     2020/7/24 17:02
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
interface MockTestService {
    @GET("info")
    suspend fun fetchInfo(): ApiResponse<InfoTest>
}