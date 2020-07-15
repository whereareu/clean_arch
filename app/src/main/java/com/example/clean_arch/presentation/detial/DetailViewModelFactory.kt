package com.example.clean_arch.presentation.detial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aliasadi.clean.presentation.util.DispatchersProvider
import com.example.clean_arch.domain.model.Movie
import com.example.clean_arch.presentation.base.BaseFragment

class DetailViewModelFactory(
    private val dispatchersProvider: DispatchersProvider
) : ViewModelProvider.Factory {

    var movie : Movie ? = null

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(
            movie,
            dispatchersProvider
        ) as T
    }
}