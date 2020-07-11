package com.example.clean_arch.data.net

import com.example.clean_arch.data.model.remote.MovieResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface MovieApi {
    @GET("movies/")
    fun getMovies(): Deferred<MovieResponse>
}