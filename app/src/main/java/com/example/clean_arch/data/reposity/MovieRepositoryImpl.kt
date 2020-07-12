package com.example.clean_arch.data.reposity

import android.util.Log
import com.example.clean_arch.domain.model.Movie
import com.example.clean_arch.domain.repository.MovieRepository
import com.example.clean_arch.domain.utils.Result

class MovieRepositoryImpl(
    private val remote: DataSource.Remote<Movie>,
    private val local: DataSource.Local<Movie>,
    private val cache: DataSource.Cache<Movie>
) : MovieRepository {

    override suspend fun getMovies(): Result<List<Movie>> {
        return getMoviesFromCacheDataSource()
    }

    private suspend fun getMoviesFromCacheDataSource(): Result<List<Movie>> {
        return when (val result = cache.getList()) {
            is Result.Success -> {
                Log.d("XXX", "Getting movies from cache")
                result
            }

            is Result.Error -> {
                getMoviesFromLocalDataSource()
            }
        }
    }

    private suspend fun getMoviesFromLocalDataSource(): Result<List<Movie>> {
        return when (val result = local.getList()) {
            is Result.Success -> {
                Log.d("XXX", "Getting movies from database")
                refreshCache(result.data)
                result
            }

            is Result.Error -> {
                getMoviesFromRemoteDataSource()
            }
        }
    }

    private suspend fun getMoviesFromRemoteDataSource(): Result<List<Movie>> {

        val result = remote.getList()

        if (result is Result.Success) {
            saveMovies(result.data)
            refreshCache(result.data)
        }

        return result
    }

    private fun saveMovies(movies: List<Movie>) {
        local.saveList(movies)
    }

    private fun refreshCache(movies: List<Movie>) {
        cache.saveList(movies)
    }

}