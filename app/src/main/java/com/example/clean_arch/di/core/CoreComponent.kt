package com.example.clean_arch.di.core

import com.example.clean_arch.di.core.module.AppModule
import com.example.clean_arch.di.core.module.DataBaseModule
import com.example.clean_arch.di.core.module.DataModule
import com.example.clean_arch.di.core.module.NetworkModule
import com.example.clean_arch.di.detail.DetailModule
import com.example.clean_arch.di.detail.DetailSubComponent
import com.example.clean_arch.di.feed.FeedModule
import com.example.clean_arch.di.feed.FeedSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    NetworkModule::class,
    DataBaseModule::class,
    DataModule::class
])
interface CoreComponent {
    fun plus(feedModule: FeedModule): FeedSubComponent
    fun plus(detailModule: DetailModule) : DetailSubComponent
}