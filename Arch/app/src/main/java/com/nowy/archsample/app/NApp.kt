package com.nowy.archsample.app

import android.app.Application
import com.blankj.utilcode.util.Utils
import com.nowy.arch.config.ArchConfig

/**
 *
 * @ClassName:      NApp
 * @Description:    作用描述
 * @Author:         Nowy
 * @CreateDate:     2020/7/31 10:12
 * @UpdateUser:
 * @UpdateDate:     2020/7/31 10:12
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class NApp :Application() {

    override fun onCreate() {
        super.onCreate()
        ArchConfig.init(this)
    }
}