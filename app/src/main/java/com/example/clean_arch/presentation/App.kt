package com.example.clean_arch.presentation

import android.app.Application
import com.example.clean_arch.BuildConfig
import com.example.clean_arch.presentation.di.DaggerInjector
import com.example.clean_arch.presentation.di.core.CoreComponent
import com.example.clean_arch.presentation.di.core.DaggerCoreComponent
import com.example.clean_arch.presentation.di.core.module.AppModule
import com.example.clean_arch.presentation.di.core.module.DataBaseModule
import com.example.clean_arch.presentation.di.core.module.DataModule
import com.example.clean_arch.presentation.di.core.module.NetworkModule
import com.example.clean_arch.presentation.di.feed.FeedModule
import com.example.clean_arch.presentation.di.feed.FeedSubComponent

class App :  Application(), DaggerInjector {
    private lateinit var coreComponent: CoreComponent

    override fun onCreate() {
        super.onCreate()
        coreComponent = DaggerCoreComponent.builder()
            .appModule(AppModule(applicationContext))
            .networkModule(NetworkModule(BuildConfig.BASE_URL))
            .dataBaseModule(DataBaseModule())
            .dataModule(DataModule())
            .build()
    }

    override fun createFeedComponent(): FeedSubComponent {
        return coreComponent.plus(FeedModule())
    }
}