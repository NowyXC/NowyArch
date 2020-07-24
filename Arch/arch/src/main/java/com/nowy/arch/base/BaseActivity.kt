package com.nowy.arch.base

import android.content.res.Resources
import android.os.Bundle
import android.os.Message
import android.util.SparseArray
import androidx.core.util.forEach
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.blankj.utilcode.util.AdaptScreenUtils
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.ScreenUtils
import com.blankj.utilcode.util.ToastUtils
import com.nowy.arch.BuildConfig
import com.nowy.arch.R
/**
 *
 * @ClassName:      BaseActivity
 * @Description:    作用描述
 * @Author:         Nowy
 * @CreateDate:     2020/7/23 11:49
 * @UpdateUser:
 * @UpdateDate:     2020/7/23 11:49
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
abstract class BaseActivity<Binding : ViewDataBinding>:DataBindingActivity<Binding>() {


    //适配UI
    override fun getResources(): Resources? {
        return if (ScreenUtils.isPortrait()) {
            AdaptScreenUtils.adaptWidth(super.getResources(), BuildConfig.ADAPT_SCREEN_WIDTH)
        } else {
            AdaptScreenUtils.adaptHeight(super.getResources(), BuildConfig.ADAPT_SCREEN_HEIGHT)
        }
    }






}