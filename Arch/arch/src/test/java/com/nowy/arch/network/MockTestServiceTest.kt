package com.nowy.arch.network

import com.nhaarman.mockitokotlin2.mock
import com.nowy.arch.MainCoroutinesRule
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

/**
 *
 * @ClassName:      MockTestServiceTest
 * @Description:    作用描述
 * @Author:         Nowy
 * @CreateDate:     2020/7/24 17:03
 * @UpdateUser:
 * @UpdateDate:     2020/7/24 17:03
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class MockTestServiceTest: ApiAbstract<MockTestService>() {

    private lateinit var service: MockTestService

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @Before
    fun initService() {
        service = createService(MockTestService::class.java)
    }

    @Throws(IOException::class)
    @Test
    fun fetchInfoFromNetworkTest() = runBlocking {
        enqueueResponse("/info.json")
        val response = requireNotNull(service.fetchInfo())
        val responseBody = requireNotNull((response as ApiResponse.Success).data)
        mockWebServer.takeRequest()

        MatcherAssert.assertThat(responseBody.info, CoreMatchers.`is`("mock info"))
    }


}