package com.example.clean_arch.di.detail

import com.example.clean_arch.presentation.detial.DetailFragment
import dagger.Subcomponent

@Subcomponent(modules = [DetailModule::class])
interface DetailSubComponent {
    fun inject(detailFragment: DetailFragment)
}