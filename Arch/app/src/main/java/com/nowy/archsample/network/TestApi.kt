package com.nowy.archsample.network

import com.nowy.archsample.model.AicCloudServerConfigVO
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

/**
 *
 * @ClassName:      TestApi
 * @Description:    作用描述
 * @Author:         Nowy
 * @CreateDate:     2020/7/29 16:08
 * @UpdateUser:
 * @UpdateDate:     2020/7/29 16:08
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
interface TestApi {
    @GET("rest/findCloudServerUrl")
    suspend fun fetchServerConfig(): ApiResponse<AicCloudServerConfigVO>
}