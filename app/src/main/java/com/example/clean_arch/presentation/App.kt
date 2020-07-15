package com.example.clean_arch.presentation

import android.app.Application
import com.example.clean_arch.BuildConfig
import com.example.clean_arch.di.core.CoreComponent
import com.example.clean_arch.di.core.DaggerCoreComponent
import com.example.clean_arch.di.core.module.AppModule
import com.example.clean_arch.di.core.module.DataBaseModule
import com.example.clean_arch.di.core.module.DataModule
import com.example.clean_arch.di.core.module.NetworkModule

class App :  Application() {
    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.builder()
            .appModule(AppModule(applicationContext))
            .networkModule(NetworkModule(BuildConfig.BASE_URL))
            .dataBaseModule(DataBaseModule())
            .dataModule(DataModule())
            .build()
    }

    fun getCore() : CoreComponent {
        return coreComponent
    }
}