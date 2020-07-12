package com.example.clean_arch.presentation.di.core.module

import com.example.clean_arch.data.utils.DiskExecutor
import com.example.clean_arch.data.db.MovieDao
import com.example.clean_arch.data.net.MovieApi
import com.example.clean_arch.data.reposity.*
import com.example.clean_arch.data.reposity.MovieCacheDataSource
import com.example.clean_arch.data.reposity.DataSource
import com.example.clean_arch.data.reposity.MovieLocalDataSource
import com.example.clean_arch.data.reposity.MovieRemoteDataSource
import com.example.clean_arch.domain.model.Movie
import com.example.clean_arch.domain.repository.MovieRepository
import com.example.clean_arch.domain.usecase.GetMoviesUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideMovieRepository(remote: DataSource.Remote<Movie>,
                               local: DataSource.Local<Movie>,
                               cache: DataSource.Cache<Movie>): MovieRepository {
        return MovieRepositoryImpl(remote, local, cache)
    }

    @Provides
    @Singleton
    fun provideMovieLocalDataSource(
        executor: DiskExecutor, movieDao: MovieDao
    ): DataSource.Local<Movie> {
        return MovieLocalDataSource(
            executor,
            movieDao
        )
    }

    @Provides
    @Singleton
    fun provideMovieCacheDataSource(): DataSource.Cache<Movie> {
        return MovieCacheDataSource()
    }


    @Provides
    @Singleton
    fun provideMovieRemoveDataSource(movieApi: MovieApi): DataSource.Remote<Movie> {
        return MovieRemoteDataSource(
            movieApi
        )
    }

    @Provides
    fun provideGetMovieUseCase(movieRepository: MovieRepository): GetMoviesUseCase {
        return GetMoviesUseCase(movieRepository)
    }
}