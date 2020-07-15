package com.example.clean_arch.di.detail

import com.aliasadi.clean.presentation.util.DispatchersProvider
import com.example.clean_arch.presentation.detial.DetailViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class DetailModule {
    @Provides
    fun provideDetailViewModelFactory(
        dispatchersProvider: DispatchersProvider
    ) : DetailViewModelFactory {
        return DetailViewModelFactory(dispatchersProvider)
    }
}