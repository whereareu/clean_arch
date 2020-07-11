package com.example.clean_arch.presentation.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aliasadi.clean.presentation.util.DispatchersProvider
import com.example.clean_arch.domain.usecase.GetMoviesUseCase

class FeedViewModelFactory(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val dispatchers: DispatchersProvider
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FeedViewModel(
            getMoviesUseCase, dispatchers
        ) as T
    }
}