package com.example.clean_arch.presentation.di.feed

import com.example.clean_arch.presentation.feed.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = [FeedModule::class])
interface FeedSubComponent {
    fun inject(feedActivity: MainActivity)
}