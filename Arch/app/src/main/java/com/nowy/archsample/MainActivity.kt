package com.nowy.archsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.nowy.arch.base.BaseActivity
import com.nowy.archsample.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val mainVM: MainVM by viewModels()

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_main)
            .addBindingParam(BR.vm,mainVM)
    }
}