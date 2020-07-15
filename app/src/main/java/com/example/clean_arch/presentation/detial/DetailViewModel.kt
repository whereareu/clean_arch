package com.example.clean_arch.presentation.detial

import androidx.lifecycle.MutableLiveData
import com.aliasadi.clean.presentation.util.DispatchersProvider
import com.example.clean_arch.domain.model.Movie
import com.example.clean_arch.presentation.base.BaseViewModel
import com.example.clean_arch.presentation.util.Event

class DetailViewModel(
    private val movie: Movie?,
    private val dispatchers: DispatchersProvider) : BaseViewModel(dispatchers) {

    private val movieLiveData: MutableLiveData<Event<Movie>> = MutableLiveData()

    fun getMovie() {
        movie?.let {
            movieLiveData.postValue(Event(it))
        }
    }

    fun getMovieLiveData() : MutableLiveData<Event<Movie>> {
        return movieLiveData
    }
}