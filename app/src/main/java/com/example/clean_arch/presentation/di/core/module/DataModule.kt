package com.example.clean_arch.presentation.di.core.module

import com.example.clean_arch.data.DiskExecutor
import com.example.clean_arch.data.db.MovieDao
import com.example.clean_arch.data.net.MovieApi
import com.example.clean_arch.data.reposity.*
import com.example.clean_arch.data.reposity.datasource.MovieCacheDataSource
import com.example.clean_arch.data.reposity.datasource.MovieDataSource
import com.example.clean_arch.data.reposity.datasource.MovieLocalDataSource
import com.example.clean_arch.data.reposity.datasource.MovieRemoteDataSource
import com.example.clean_arch.domain.repository.MovieRepository
import com.example.clean_arch.domain.usecase.GetMoviesUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideMovieRepository(movieRemote: MovieDataSource.Remote,
                               movieLocal: MovieDataSource.Local,
                               movieCache: MovieDataSource.Cache): MovieRepository {
        return MovieRepositoryImpl(movieRemote, movieLocal, movieCache)
    }

    @Provides
    @Singleton
    fun provideMovieLocalDataSource(
        executor: DiskExecutor, movieDao: MovieDao
    ): MovieDataSource.Local {
        return MovieLocalDataSource(
            executor,
            movieDao
        )
    }

    @Provides
    @Singleton
    fun provideMovieCacheDataSource(): MovieDataSource.Cache {
        return MovieCacheDataSource()
    }


    @Provides
    @Singleton
    fun provideMovieRemoveDataSource(movieApi: MovieApi): MovieDataSource.Remote {
        return MovieRemoteDataSource(
            movieApi
        )
    }

    @Provides
    fun provideGetMovieUseCase(movieRepository: MovieRepository): GetMoviesUseCase {
        return GetMoviesUseCase(movieRepository)
    }
}