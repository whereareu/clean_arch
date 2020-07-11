package com.example.clean_arch.domain.usecase

import com.example.clean_arch.domain.model.Movie
import com.example.clean_arch.domain.repository.MovieRepository
import com.example.clean_arch.domain.utils.Result

open class GetMoviesUseCase(private val repository: MovieRepository) {
    suspend fun execute(): Result<List<Movie>> {
        return repository.getMovies()
    }
}