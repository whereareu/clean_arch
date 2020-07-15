package com.example.clean_arch.data.reposity

import android.util.SparseArray
import com.example.clean_arch.data.exception.NoDataException
import com.example.clean_arch.domain.model.Movie
import com.example.clean_arch.domain.utils.Result

class MovieCacheDataSource :
    DataSource.Cache {
    private val cachedMovies = SparseArray<Movie>()

    override suspend fun getList(): Result<List<Movie>> {
        return if (cachedMovies.size() > 0) {
            val movies = arrayListOf<Movie>()
            for (i in 0 until cachedMovies.size()) {
                val key = cachedMovies.keyAt(i)
                movies.add(cachedMovies[key])
            }
            Result.Success(movies)
        } else {
            Result.Error(NoDataException())
        }
    }

    override fun saveList(movies: List<Movie>) {
        cachedMovies.clear()
        for (movie in movies) {
            cachedMovies.put(movie.id, movie)
        }
    }
}