package com.nowy.arch.base

import androidx.databinding.ViewDataBinding

/**
 *
 * @ClassName:      BaseFragment
 * @Description:    作用描述
 * @Author:         Nowy
 * @CreateDate:     2020/7/23 18:13
 * @UpdateUser:
 * @UpdateDate:     2020/7/23 18:13
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
abstract class BaseFragment<Binding : ViewDataBinding>:DataBindingActivity<Binding>() {
}