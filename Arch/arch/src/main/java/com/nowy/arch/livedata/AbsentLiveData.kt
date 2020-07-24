package com.nowy.arch.livedata

import androidx.lifecycle.LiveData

/**
 * @ClassName:      AbsentLiveData
 * @Description:    发送空事件的LiveData
 * @Author:         Nowy
 * @CreateDate:     2020/7/23 11:27
 * @UpdateUser:
 * @UpdateDate:     2020/7/23 11:27
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */

class AbsentLiveData<T : Any?> private constructor(): LiveData<T>() {
    init {
        // use post instead of set since this can be created on any thread
        postValue(null)
    }

    companion object {
        fun <T> create(): LiveData<T> {
            return AbsentLiveData()
        }
    }
}