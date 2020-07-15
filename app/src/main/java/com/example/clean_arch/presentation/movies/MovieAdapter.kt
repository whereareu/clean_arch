package com.example.clean_arch.presentation.movies

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.clean_arch.R
import com.example.clean_arch.domain.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*
import java.util.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var items: List<Movie> = ArrayList()
    private var listener: ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = items[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setMovieClickListener(listener: (Movie) -> Unit) {
        this.listener = listener
    }

    fun setItems(items: List<Movie>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class MovieViewHolder internal constructor(itemView: View) : ViewHolder(itemView) {

        fun bind(movie: Movie) {
            itemView.apply {
                title.text = movie.title
                desc.text = movie.description
                Glide.with(context).load(movie.image).into(image)
                setOnClickListener { listener?.invoke(movie) }
            }
        }
    }


}