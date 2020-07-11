package com.example.clean_arch.presentation.di.feed

import com.aliasadi.clean.presentation.util.DispatchersProvider
import com.example.clean_arch.domain.usecase.GetMoviesUseCase
import com.example.clean_arch.presentation.feed.FeedViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class FeedModule {
    @Provides
    fun provideFeedViewModelFactory(getMoviesUseCase: GetMoviesUseCase, dispatchers: DispatchersProvider): FeedViewModelFactory {
        return FeedViewModelFactory(getMoviesUseCase, dispatchers)
    }
}