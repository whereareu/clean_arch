package com.example.clean_arch.presentation.di

import com.example.clean_arch.presentation.di.core.module.AppModule
import com.example.clean_arch.presentation.di.core.module.DataBaseModule
import com.example.clean_arch.presentation.di.core.module.DataModule
import com.example.clean_arch.presentation.di.core.module.NetworkModule
import com.example.clean_arch.presentation.di.feed.FeedSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    NetworkModule::class,
    DataBaseModule::class,
    DataModule::class
])
interface DaggerInjector {
    fun createFeedComponent(): FeedSubComponent
}