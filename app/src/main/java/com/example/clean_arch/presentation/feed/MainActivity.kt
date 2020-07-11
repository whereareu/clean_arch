package com.example.clean_arch.presentation.feed

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.clean_arch.R
import com.example.clean_arch.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<FeedViewModel>(R.layout.activity_main) {
    @Inject
    lateinit var factory: FeedViewModelFactory

    private var movieAdapter = MovieAdapter()

    override fun createViewModel(): FeedViewModel {
        return ViewModelProviders.of(this, factory).get(FeedViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        daggerInjector.createFeedComponent().inject(this)
        super.onCreate(savedInstanceState)

        recyclerView.adapter = movieAdapter

        network.setOnClickListener {
            viewModel.getMovies()
        }

        movieAdapter.setMovieClickListener { movie ->
            viewModel.onMovieClicked(movie)
        }

        viewModel.getHideLoadingLiveData().observe {
            progressBar.visibility = View.GONE
        }

        viewModel.getShowLoadingLiveData().observe {
            progressBar.visibility = View.VISIBLE
        }

        viewModel.getMoviesLiveData().observe { movies ->
            movieAdapter.setItems(movies)
        }

        viewModel.getShowErrorLiveData().observe { error ->
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        }
    }
}