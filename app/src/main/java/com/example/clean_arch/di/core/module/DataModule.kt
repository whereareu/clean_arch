package com.example.clean_arch.di.core.module

import com.example.clean_arch.data.utils.DiskExecutor
import com.example.clean_arch.data.db.MovieDao
import com.example.clean_arch.data.api.MovieApi
import com.example.clean_arch.data.reposity.*
import com.example.clean_arch.data.reposity.MovieCacheDataSource
import com.example.clean_arch.data.reposity.DataSource
import com.example.clean_arch.data.reposity.MovieLocalDataSource
import com.example.clean_arch.data.reposity.MovieRemoteDataSource
import com.example.clean_arch.domain.repository.MovieRepository
import com.example.clean_arch.domain.usecase.GetMoviesUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideMovieRepository(remote: DataSource.Remote,
                               local: DataSource.Local,
                               cache: DataSource.Cache): MovieRepository {
        return MovieRepositoryImpl(remote, local, cache)
    }

    @Provides
    @Singleton
    fun provideMovieLocalDataSource(
        executor: DiskExecutor, movieDao: MovieDao
    ): DataSource.Local {
        return MovieLocalDataSource(
            executor,
            movieDao
        )
    }

    @Provides
    @Singleton
    fun provideMovieCacheDataSource(): DataSource.Cache {
        return MovieCacheDataSource()
    }


    @Provides
    @Singleton
    fun provideMovieRemoveDataSource(movieApi: MovieApi): DataSource.Remote {
        return MovieRemoteDataSource(
            movieApi
        )
    }

    @Provides
    fun provideGetMovieUseCase(movieRepository: MovieRepository): GetMoviesUseCase {
        return GetMoviesUseCase(movieRepository)
    }
}