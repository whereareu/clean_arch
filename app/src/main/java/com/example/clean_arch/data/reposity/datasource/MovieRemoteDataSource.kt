package com.example.clean_arch.data.reposity.datasource

import com.example.clean_arch.data.mapper.MovieMapper
import com.example.clean_arch.data.net.MovieApi
import com.example.clean_arch.domain.model.Movie
import com.example.clean_arch.domain.utils.Result
import java.lang.Exception

class MovieRemoteDataSource(private val movieApi: MovieApi) :
    MovieDataSource.Remote {
    override suspend fun getMovies(): Result<List<Movie>> {
        return try {
            val result = movieApi.getMovies().await()
            Result.Success(result.movies.map { MovieMapper.toDomain(it) })
        }
        catch (e : Exception) {
            Result.Error(e)
        }
    }

}