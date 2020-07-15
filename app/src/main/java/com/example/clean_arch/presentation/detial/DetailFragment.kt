package com.example.clean_arch.presentation.detial

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.clean_arch.R
import com.example.clean_arch.presentation.base.BaseFragment
import com.example.clean_arch.di.core.CoreComponent
import com.example.clean_arch.di.detail.DetailModule
import com.example.clean_arch.domain.model.Movie
import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.android.synthetic.main.detail_fragment.*
import javax.inject.Inject

class DetailFragment : BaseFragment<DetailViewModel>(R.layout.detail_fragment) {
    @Inject
    lateinit var factory: DetailViewModelFactory

    var movie : Movie ? = null

    override fun injectComponent(coreComponent: CoreComponent) {
        coreComponent.plus(DetailModule()).inject(this)
    }

    override fun createViewModel(): DetailViewModel {
        factory.movie = movie
        return ViewModelProviders.of(this, factory).get(DetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        movie = arguments?.getParcelable("EXTRA_MOVIE")
        super.onCreate(savedInstanceState)
    }

    override fun observeViewModel() {
        viewModel.getMovieLiveData().observe{
            movieTitle.text = it.title
            description.text = it.description
            Glide.with(activity).load(it.image).into(image)
        }
    }

    override fun init() {
        activity.toolbar.visibility = View.VISIBLE
        viewModel.getMovie()
    }
}