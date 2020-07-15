package com.example.clean_arch.presentation.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aliasadi.clean.presentation.util.DispatchersProvider
import com.example.clean_arch.domain.model.Movie
import com.example.clean_arch.domain.usecase.GetMoviesUseCase
import com.example.clean_arch.domain.utils.Result
import com.example.clean_arch.presentation.base.BaseViewModel
import com.example.clean_arch.presentation.util.Event

class MoviesViewModel internal constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    dispatchers: DispatchersProvider) : BaseViewModel(dispatchers) {

    private val moviesLiveData: MutableLiveData<Event<List<Movie>>> = MutableLiveData()
    private val showLoadingLiveData: MutableLiveData<Event<Unit>> = MutableLiveData()
    private val hideLoadingLiveData: MutableLiveData<Event<Unit>> = MutableLiveData()
    private val showErrorLiveData : MutableLiveData<Event<String>> = MutableLiveData()
    private val navigateToMovieDetails: MutableLiveData<Event<Movie>> = MutableLiveData()

    fun getMovies() {
        showLoadingLiveData.postValue(Event(Unit))

        execute {
            when (val result = getMoviesUseCase.execute()) {
                is Result.Success -> {
                    hideLoadingLiveData.postValue(Event(Unit))
                    moviesLiveData.postValue(Event(result.data))
                }

                is Result.Error -> {
                    hideLoadingLiveData.postValue(Event(Unit))
                    showErrorLiveData.postValue(Event(result.error.message) as Event<String>?)
                }
            }
        }
    }

    fun onMovieClicked(movie: Movie) {
        navigateToMovieDetails.postValue(Event(movie))
    }

    fun getMoviesLiveData(): LiveData<Event<List<Movie>>> = moviesLiveData
    fun getShowLoadingLiveData(): LiveData<Event<Unit>> = showLoadingLiveData
    fun getHideLoadingLiveData(): LiveData<Event<Unit>> = hideLoadingLiveData
    fun getShowErrorLiveData(): LiveData<Event<String>> = showErrorLiveData
    fun getNavigateToMovieDetails(): LiveData<Event<Movie>> = navigateToMovieDetails
}