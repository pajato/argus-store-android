package com.pajato.argus.store.ui.movie

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pajato.argus.store.repo.MovieModel
import kotlinx.android.synthetic.main.movie_item.view.movieName
import kotlinx.android.synthetic.main.movie_item.view.popularity

class MovieItemHolder(private val item: View) : RecyclerView.ViewHolder(item) {
    private val name: TextView = item.movieName
    private val rating: TextView = item.popularity

    fun bindTo(model: MovieModel) {
        name.text = model.movie.original_title
        rating.text = model.rating
    }
}