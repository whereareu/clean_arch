package com.example.clean_arch.domain.repository

import com.example.clean_arch.domain.model.Movie
import com.example.clean_arch.domain.utils.Result

interface MovieRepository {
    suspend fun getMovies(): Result<List<Movie>>
}