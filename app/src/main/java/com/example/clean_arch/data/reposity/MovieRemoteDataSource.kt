package com.example.clean_arch.data.reposity

import com.example.clean_arch.data.mapper.MovieMapper
import com.example.clean_arch.data.api.MovieApi
import com.example.clean_arch.domain.model.Movie
import com.example.clean_arch.domain.utils.Result
import java.lang.Exception

class MovieRemoteDataSource(private val movieApi: MovieApi) :
    DataSource.Remote {
    override suspend fun getList(): Result<List<Movie>> {
        return try {
            val result = movieApi.getMovies().await()
            Result.Success(result.movies.map { MovieMapper.toDomain(it) })
        }
        catch (e : Exception) {
            Result.Error(e)
        }
    }

}