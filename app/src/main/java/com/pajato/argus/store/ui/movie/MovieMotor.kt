package com.pajato.argus.store.ui.movie

import androidx.lifecycle.ViewModel
import com.pajato.argus.store.pager.Pager
import com.pajato.argus.store.repo.MovieModel
import com.pajato.argus.store.repo.MovieRepository
import kotlinx.serialization.UnstableDefault

@UnstableDefault
class MovieMotor(private val repo: MovieRepository) : ViewModel() {

    val items: List<MovieModel>
        get() = repo.items

    fun add(model: MovieModel) = repo.add(model)

    fun modify(model: MovieModel) = repo.modify(model)

    fun remove(model: MovieModel) = repo.remove(model)

    fun fetchMovies(): List<MovieModel> {
        val newMovieModels = Pager().getSomeMovies()
        repo.items.map { repo.remove(it) }
        newMovieModels.map { repo.add(it) }
        return items
    }

    fun clearMovies(): List<MovieModel> {
        repo.items.map { repo.remove(it) }
        return items
    }
}
