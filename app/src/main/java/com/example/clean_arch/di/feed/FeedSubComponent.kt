package com.example.clean_arch.di.feed

import com.example.clean_arch.presentation.movies.MoviesFragment
import dagger.Subcomponent

@Subcomponent(modules = [FeedModule::class])
interface FeedSubComponent {
    fun inject(MoviesFragment: MoviesFragment)
}