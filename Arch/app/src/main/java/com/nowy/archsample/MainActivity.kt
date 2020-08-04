package com.nowy.archsample
import android.os.Bundle
import androidx.activity.viewModels
import com.nowy.arch.base.BaseActivity
import com.nowy.archsample.databinding.ActivityMainBinding
import com.nowy.archsample.BR

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val mainVM: MainVM by viewModels()

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_main)
            .addBindingParam(BR.vm,mainVM)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        mainVM.test()
        mainVM.testWithContext()
    }
}