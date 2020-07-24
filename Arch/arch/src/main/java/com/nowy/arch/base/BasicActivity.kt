package com.nowy.arch.base

import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.ToastUtils
import com.nowy.arch.R

/**
 *
 * @ClassName:      BasicActivity
 * @Description:    作用描述
 * @Author:         Nowy
 * @CreateDate:     2020/7/23 17:17
 * @UpdateUser:
 * @UpdateDate:     2020/7/23 17:17
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
abstract class BasicActivity:AppCompatActivity() {

    protected var dialog: MaterialDialog? = null


    open fun showLongToast(data:Any?){
        if(data == null) return
        when(data){
            is Int -> ToastUtils.showLong(data)
            is CharSequence -> ToastUtils.showLong(data)
            else -> ToastUtils.showLong("%s", GsonUtils.toJson(data))
        }
    }

    open fun showShortToast(data:Any?){
        if(data == null) return
        when(data){
            is Int -> ToastUtils.showShort(data)
            is CharSequence -> ToastUtils.showShort(data)
            else -> ToastUtils.showShort("%s", GsonUtils.toJson(data))
        }
    }

    /**
     * 打开等待框
     */
    open fun showLoading() {
        if (dialog == null) {
            dialog = MaterialDialog(this)
                .cancelable(false)
                .cornerRadius(8f)
                .customView(R.layout.arch_dialog_loading, noVerticalPadding = true)
                .lifecycleOwner(this)
                .maxWidth(R.dimen.arch_loading_dialog_width)
        }
        dialog?.show()
    }

    /**
     * 关闭等待框
     */
    open fun dismissLoading() {
        dialog?.run { if (isShowing) dismiss() }
    }

}