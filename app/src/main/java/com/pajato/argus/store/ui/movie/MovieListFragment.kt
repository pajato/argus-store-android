package com.pajato.argus.store.ui.movie

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.pajato.argus.store.R
import com.pajato.argus.store.repo.MovieModel
import com.pajato.argus.store.ui.MainActivity
import kotlinx.android.synthetic.main.movie_list.fab
import kotlinx.android.synthetic.main.movie_list_content.empty
import kotlinx.android.synthetic.main.movie_list_content.items
import kotlinx.serialization.UnstableDefault
import org.koin.androidx.viewmodel.ext.android.viewModel

@UnstableDefault
class MovieListFragment : Fragment() {
    private val motor: MovieMotor by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        val activity: MainActivity = context as MainActivity
        activity.movieListFragment = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.movie_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fun setupFab() {
            fab.setOnClickListener { view ->
                Snackbar.make(view, "Fetching new movies", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
                updateDisplay(motor.fetchMovies())
            }
        }
        fun setupRecyclerView(adapter: MovieAdapter) {
            items.apply {
                this.adapter = adapter
                adapter.submitList(motor.items)
                layoutManager = LinearLayoutManager(context)
                addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            }
        }

        super.onViewCreated(view, savedInstanceState)
        setupFab()
        setupRecyclerView(MovieAdapter(layoutInflater))
        displayFragment()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.actions_movie, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        fun resetItems() = updateDisplay(motor.clearMovies())

        return when (item.itemId) {
            R.id.clear_all -> { resetItems(); true }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun displayFragment() {
        fun setDisplay(itemCount: Int) {
            when (itemCount) {
                0 -> empty.visibility = View.VISIBLE
                else -> empty.visibility = View.GONE
            }
        }

        val itemCount = motor.items.size
        setDisplay(itemCount)
    }

    private fun updateDisplay(movies: List<MovieModel>) {
        val adapter = items.adapter as MovieAdapter
        adapter.submitList(movies)
        adapter.notifyDataSetChanged()
        displayFragment()
    }


}