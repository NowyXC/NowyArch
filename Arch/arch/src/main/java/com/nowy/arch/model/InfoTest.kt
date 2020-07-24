package com.nowy.arch.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 *
 * @ClassName:      Info
 * @Description:    作用描述
 * 作为单元测试的一个model类，
 * 放在test目录moshi报找不到generateAdapter
 *
 * @Author:         Nowy
 * @CreateDate:     2020/7/24 17:11
 * @UpdateUser:
 * @UpdateDate:     2020/7/24 17:11
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
@JsonClass(generateAdapter = true)
data class InfoTest(
    @field:Json(name = "info") val info:String
)