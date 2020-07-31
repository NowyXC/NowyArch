package com.nowy.arch.config

import android.app.Application
import android.content.Context
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.nowy.arch.network.RetrofitClient

/**
 *
 * @ClassName:      ArchConfig
 * @Description:    作用描述
 * @Author:         Nowy
 * @CreateDate:     2020/7/31 10:13
 * @UpdateUser:
 * @UpdateDate:     2020/7/31 10:13
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
object ArchConfig {

    fun init(application: Application){
        Utils.init(application)

        LogUtils
            .getConfig()
            .globalTag = "NowyArch"

        RetrofitClient.config(true)
    }
}