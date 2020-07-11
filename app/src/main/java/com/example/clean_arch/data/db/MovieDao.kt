package com.example.clean_arch.data.db

import androidx.room.*
import com.example.clean_arch.data.model.local.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    fun getMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovies(movies: List<MovieEntity?>?)

    @Query("DELETE FROM movies")
    fun deleteMovies()
}