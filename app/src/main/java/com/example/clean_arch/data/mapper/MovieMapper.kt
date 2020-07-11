package com.example.clean_arch.data.mapper

import com.example.clean_arch.data.model.local.MovieEntity
import com.example.clean_arch.data.model.remote.MovieRemote
import com.example.clean_arch.domain.model.Movie

object MovieMapper {
    fun toDomain(movieRemote: MovieRemote): Movie {
        return Movie(
            id = movieRemote.id,
            image = movieRemote.image,
            description = movieRemote.description,
            title = movieRemote.title
        )
    }

    fun toDomain(entity: MovieEntity): Movie {
        return Movie(
            id = entity.id,
            image = entity.image,
            description = entity.description,
            title = entity.title
        )
    }

    fun toEntity(movie: Movie) : MovieEntity {
        return MovieEntity(
            id = movie.id,
            image = movie.image,
            description = movie.description,
            title = movie.title
        )
    }
}