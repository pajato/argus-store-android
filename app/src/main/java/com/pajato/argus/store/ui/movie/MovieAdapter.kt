package com.pajato.argus.store.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.pajato.argus.store.R
import com.pajato.argus.store.repo.MovieModel

class MovieAdapter(private val inflater: LayoutInflater) : ListAdapter<MovieModel, MovieItemHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemHolder =
        MovieItemHolder(
            inflater.inflate(
                R.layout.movie_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MovieItemHolder, position: Int) {
        holder.bindTo(getItem(position))
    }
}

internal object DiffCallback : DiffUtil.ItemCallback<MovieModel>() {

    override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel) = oldItem.movie.id == newItem.movie.id

    override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel) =
        oldItem.movie.adult == newItem.movie.adult &&
                oldItem.movie.id == newItem.movie.id &&
                oldItem.movie.original_title == newItem.movie.original_title &&
                oldItem.movie.popularity == newItem.movie.popularity &&
                oldItem.movie.video == newItem.movie.video
}