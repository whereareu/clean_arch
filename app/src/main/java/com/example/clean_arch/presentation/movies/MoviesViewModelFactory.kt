package com.example.clean_arch.presentation.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aliasadi.clean.presentation.util.DispatchersProvider
import com.example.clean_arch.domain.usecase.GetMoviesUseCase

class MoviesViewModelFactory(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val dispatchers: DispatchersProvider
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoviesViewModel(
            getMoviesUseCase, dispatchers
        ) as T
    }
}