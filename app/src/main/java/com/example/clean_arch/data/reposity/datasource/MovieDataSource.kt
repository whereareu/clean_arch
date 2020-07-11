package com.example.clean_arch.data.reposity.datasource

import com.example.clean_arch.domain.model.Movie
import com.example.clean_arch.domain.utils.Result

interface MovieDataSource {
    interface Remote {
        suspend fun getMovies(): Result<List<Movie>>
    }

    interface Local :
        Remote {
        fun saveMovies(movies: List<Movie>)
    }

    interface Cache :
        Local

}