package com.example.clean_arch.data.reposity

import com.example.clean_arch.domain.model.Movie
import com.example.clean_arch.domain.utils.Result

interface DataSource {
    interface Remote {
        suspend fun getList(): Result<List<Movie>>
    }

    interface Local : Remote {
        fun saveList(list: List<Movie>)
    }

    interface Cache : Local

}