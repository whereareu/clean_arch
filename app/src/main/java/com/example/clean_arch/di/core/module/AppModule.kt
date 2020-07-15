package com.example.clean_arch.di.core.module

import android.content.Context
import com.aliasadi.clean.presentation.util.DispatchersProvider
import com.aliasadi.clean.presentation.util.DispatchersProviderImpl
import com.example.clean_arch.data.utils.DiskExecutor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule constructor(context: Context) {

    private val appContext = context.applicationContext

    @Singleton
    @Provides
    fun provideAppContext(): Context {
        return appContext
    }

    @Provides
    fun provideDiskExecutor(): DiskExecutor {
        return DiskExecutor()
    }

    @Provides
    fun provideDispatchers() : DispatchersProvider {
        return DispatchersProviderImpl()
    }

}