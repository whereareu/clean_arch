package com.example.clean_arch.data.reposity

import com.example.clean_arch.domain.model.Movie
import com.example.clean_arch.domain.utils.Result

interface DataSource<T> {
    interface Remote<T> {
        suspend fun getList(): Result<List<T>>
    }

    interface Local<T> : Remote<T> {
        fun saveList(list: List<T>)
    }

    interface Cache<T> : Local<T>

}