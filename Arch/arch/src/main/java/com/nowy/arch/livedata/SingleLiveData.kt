package com.nowy.arch.livedata

import androidx.annotation.MainThread
import androidx.annotation.Nullable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean


/**
 *
 * @ClassName:      SingleLiveData
 * @Description:    处理LiveData多次回调数据问题
 * @Author:         Nowy
 * @CreateDate:     2020/7/23 11:27
 * @UpdateUser:
 * @UpdateDate:     2020/7/23 11:27
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class SingleLiveData<T> :MutableLiveData<T>() {
    private val atomic = AtomicBoolean(false)

    override fun observe(owner: LifecycleOwner, observer: Observer<in T?>) {
        super.observe(owner, Observer<T> { t ->
            if (atomic.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }

    @MainThread
    override fun setValue(@Nullable t: T?) {
        atomic.set(true)
        super.setValue(t)
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    fun call() {
        value = null
    }
}