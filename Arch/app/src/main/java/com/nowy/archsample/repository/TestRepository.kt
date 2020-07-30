package com.nowy.archsample.repository

import com.nowy.archsample.model.AicCloudServerConfigVO
import com.nowy.archsample.network.TestApiClient
import com.skydoves.sandwich.*
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 *
 * @ClassName:      TestRepository
 * @Description:    作用描述
 * @Author:         Nowy
 * @CreateDate:     2020/7/29 16:33
 * @UpdateUser:
 * @UpdateDate:     2020/7/29 16:33
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class TestRepository(private val testApiClient: TestApiClient) {

    suspend fun fetchServerConfig(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    )= flow<AicCloudServerConfigVO?>{
        val response = testApiClient.fetchServerConfig()
        response.suspendOnSuccess {
                data.whatIfNotNull { response ->
                    emit(response)
                    onSuccess()
                }
            }
            .onError {
                onError(message())
            }
            // handle the case when the API request gets an exception response.
            // e.g. network connection error.
            .onException {
                onError(message())
            }
    }.flowOn(Dispatchers.IO)





}