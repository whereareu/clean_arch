package com.example.clean_arch.presentation.movies

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.clean_arch.R
import com.example.clean_arch.presentation.base.BaseFragment
import com.example.clean_arch.di.core.CoreComponent
import com.example.clean_arch.di.feed.FeedModule
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.feed_fragment.*
import javax.inject.Inject

class MoviesFragment : BaseFragment<MoviesViewModel>(R.layout.feed_fragment) {

    @Inject
    lateinit var factory : MoviesViewModelFactory

    private var movieAdapter = MovieAdapter()

    override fun injectComponent(coreComponent: CoreComponent) {
        coreComponent.plus(FeedModule()).inject(this)
    }

    override fun createViewModel(): MoviesViewModel {
        return ViewModelProviders.of(this, factory).get(MoviesViewModel::class.java)
    }

    override fun init() {
        activity.toolbar.visibility = View.GONE

        recyclerView?.adapter = movieAdapter

        network?.setOnClickListener {
            viewModel.getMovies()
        }

        movieAdapter.setMovieClickListener { movie ->
            viewModel.onMovieClicked(movie)
        }
    }

    override fun observeViewModel() {
        viewModel.getHideLoadingLiveData().observe {
            progressBar.visibility = View.GONE
        }

        viewModel.getShowLoadingLiveData().observe {
            Log.d("FeedViewModel", "showLoading")
            progressBar.visibility = View.VISIBLE
        }

        viewModel.getMoviesLiveData().observe { movies ->
            Log.d("FeedViewModel", "It's onClick")
            movieAdapter.setItems(movies)
        }

        viewModel.getShowErrorLiveData().observe { error ->
            Toast.makeText(activity, error, Toast.LENGTH_LONG).show()
        }

        viewModel.getNavigateToMovieDetails().observe{
            val bundle = Bundle()
            bundle.putParcelable("EXTRA_MOVIE", it)
            findNavController().navigate(R.id.detailFragment, bundle)
        }
    }
}