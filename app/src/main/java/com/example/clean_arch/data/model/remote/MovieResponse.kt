package com.example.clean_arch.data.model.remote

import com.example.clean_arch.data.model.remote.MovieRemote
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("movies")
    val movies: List<MovieRemote>
)