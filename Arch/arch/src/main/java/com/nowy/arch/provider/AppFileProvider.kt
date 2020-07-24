package com.nowy.arch.provider

import android.content.Context
import androidx.core.content.FileProvider

/**
 *
 * @ClassName:      AppFileProvider
 * @Description:    兼容7.0的文件读取限制模式
 * <provider
 *    android:name=".provider.AppFileProvider"
 *    android:authorities="${applicationId}.app.provider"
 *    android:exported="false"
 *    android:grantUriPermissions="true"
 *    android:multiprocess="true">
 *    <meta-data
 *    android:name="android.support.FILE_PROVIDER_PATHS"
 *    android:resource="@xml/file_paths"/>
 * </provider>
 *
 * @Author:         Nowy
 * @CreateDate:     2020/6/9 17:32
 * @UpdateUser:
 * @UpdateDate:     2020/6/9 17:32
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class AppFileProvider: FileProvider(){
    companion object{
        /**
         * Get the provider of the external file path.
         *
         * @param context context.
         * @return provider.
         */
        fun getFileProviderName(context: Context): String {
            return context.packageName + ".app.provider"
        }
    }

}