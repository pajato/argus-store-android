package com.pajato.argus.store.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.pajato.argus.store.R
import com.pajato.argus.store.ui.movie.MovieListFragment
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.serialization.UnstableDefault

@UnstableDefault
class MainActivity : AppCompatActivity() {
    // Support the testing/coverage effort to get 100% coverage of the opOptionsItemSelected() method.
    private lateinit var savedMenuForTestCoverage: Menu
    internal fun getMenu(): Menu = savedMenuForTestCoverage
    internal lateinit var movieListFragment: MovieListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.actions, menu)
        savedMenuForTestCoverage = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        fun startAboutActivtiy(): Boolean {
            startActivity(Intent(this, AboutActivity::class.java))
            return true
        }

        return when (item.itemId) {
            R.id.about -> startAboutActivtiy()
            else -> super.onOptionsItemSelected(item)
        }
    }
}
