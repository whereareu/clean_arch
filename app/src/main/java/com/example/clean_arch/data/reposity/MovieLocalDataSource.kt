package com.example.clean_arch.data.reposity

import com.example.clean_arch.data.utils.DiskExecutor
import com.example.clean_arch.data.db.MovieDao
import com.example.clean_arch.data.exception.NoDataException
import com.example.clean_arch.data.mapper.MovieMapper
import com.example.clean_arch.domain.model.Movie
import com.example.clean_arch.domain.utils.Result

class MovieLocalDataSource (
    private val executor: DiskExecutor,
    private val movieDao: MovieDao
) : DataSource.Local<Movie> {

    override suspend fun getList(): Result<List<Movie>> {
        val movies = movieDao.getMovies()
        return if (movies.isNotEmpty()) {
            Result.Success(movies.map { MovieMapper.toDomain(it) })
        } else {
            Result.Error(NoDataException())
        }
    }

    override fun saveList(movies: List<Movie>) {
        executor.execute {
            movieDao.saveMovies(movies.map { MovieMapper.toEntity(it) })
        }
    }
}