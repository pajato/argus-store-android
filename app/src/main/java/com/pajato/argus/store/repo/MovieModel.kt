package com.pajato.argus.store.repo

import com.pajato.tmdb.core.Movie

class MovieModel(val movie: Movie) {
    val name = movie.original_title
    val rating = "${movie.popularity}%"
}