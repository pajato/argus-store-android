package com.pajato.argus.store.repo

import com.pajato.argus.store.ArgusFileStoreApp
import com.pajato.argus.store.Store
import com.pajato.tmdb.core.Movie
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json.Companion.stringify

@UnstableDefault
class MovieRepository(name: String = "movie_repo") {
    private val dir = ArgusFileStoreApp.filesDirPath
    val store = Store(dir, name)
    val itemsMap: MutableMap<String, MovieModel> by lazy {
        val map = mutableMapOf<String, MovieModel>()
        store.repo.values.map { map.put(it.id.toString(), MovieModel(it)) }
        map
    }
    val items: List<MovieModel>
        get() = itemsMap.values.toList()

    fun add(model: MovieModel) {
        val key = model.movie.id.toString()
        store.create(key, stringify(Movie.serializer(), model.movie))
        itemsMap.put(key, model)
    }

    fun modify(model: MovieModel) {
        val key = model.movie.id.toString()
        store.update(key, stringify(Movie.serializer(), model.movie))
        itemsMap.put(key, model)
    }

    fun remove(model: MovieModel) {
        val key = model.movie.id.toString()
        store.delete(key)
        itemsMap.remove(key)
    }
}
