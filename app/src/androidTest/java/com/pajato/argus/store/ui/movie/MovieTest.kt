package com.pajato.argus.store.ui.movie

import android.content.pm.ActivityInfo
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.pajato.argus.store.R
import com.pajato.argus.store.repo.MovieModel
import com.pajato.argus.store.repo.MovieRepository
import com.pajato.argus.store.ui.MainActivity
import com.pajato.tmdb.core.Movie
import kotlinx.serialization.UnstableDefault
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@UnstableDefault
@RunWith(AndroidJUnit4::class)
class MovieTest {
    @Rule
    @JvmField val activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun verifyMovieModelForCoverage() {
        val name = "some name"
        val movie = Movie(false, 1, name, 1.23, true)
        val model = MovieModel(movie)
        assertEquals(name, model.name)
    }

    @Test
    fun verifyAddModifyRemove() {
        // Check add.
        val fileName = "test_repo"
        var repo = MovieRepository(fileName)
        val motor = MovieMotor(repo)
        val id = motor.items.size + 1
        var expectedSize = id
        var movie = Movie(false, id, "some name", 1.23, true)
        var model = MovieModel(movie)
        motor.add(model)
        assertEquals(expectedSize, motor.items.size, "Adding a model did not bump the item count!")

        repo = MovieRepository(fileName)
        assertEquals(expectedSize, repo.store.repo.size, "Store repository has wrong size!")

        // Check modify.
        movie = Movie(false, id, "some name", 3.21, true)
        model = MovieModel(movie)
        motor.modify(model)
        assertEquals(expectedSize, motor.items.size, "Modifying a model yields the wrong motor item count!")

        repo = MovieRepository(fileName)
        assertEquals(expectedSize, repo.store.repo.size, "Store repository has wrong size!")

        // Check remove.
        motor.remove(model)
        repo = MovieRepository(fileName)
        expectedSize -= 1
        assertEquals(expectedSize, motor.items.size, "Removing a model yields the wrong motor item count!")
        assertEquals(expectedSize, repo.store.repo.size, "Store repository has wrong size!")

        // Clear the file.
        repo.store.file.clear()
    }

    @Test
    fun verifyClearAll() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.fab)).check(matches(isDisplayed())).perform(click())
        onView(withId(R.id.empty)).check((matches(not(isDisplayed()))))
        onView(withId(R.id.clear_all)).check(matches(isDisplayed())).perform(click())
        onView(withId(R.id.empty)).check((matches(isDisplayed())))
    }

    @Test
    fun whenTheConfigurationIsChangedVerifyEmptyView() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.clear_all)).check(matches(isDisplayed())).perform(click())
        onView(withId(R.id.empty)).check((matches(isDisplayed())))
        activityRule.activity.runOnUiThread {
            activityRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        onView(withId(R.id.clear_all)).check(matches(isDisplayed())).perform(click())
        onView(withId(R.id.empty)).check((matches(isDisplayed())))
    }

    @Test
    fun verifyAdapterItemsAreTheSame() {
        val movie1 = Movie(false, 1, "some name", 1.23, true)
        var movie2 = Movie(false, 1, "some name", 1.23, true)
        val model1 = MovieModel(movie1)
        var model2 = MovieModel(movie2)
        assertTrue(DiffCallback.areItemsTheSame(model1, model1), "An item is not itself?!?")
        assertTrue(DiffCallback.areContentsTheSame(model1, model2), "Two identical items are not the same!")
        assertTrue(DiffCallback.areItemsTheSame(model1, model2), "Items are reported as the same!")
        movie2 = Movie(false, 2, "some name", 1.23, true)
        model2 = MovieModel(movie2)
        assertFalse(DiffCallback.areItemsTheSame(model1, model2), "Different Items report as the same!")
        assertFalse(DiffCallback.areContentsTheSame(model1, model2), "Different items report as the same!")

        movie2 = Movie(true, 1, "some name", 1.23, true)
        model2 = MovieModel(movie2)
        assertFalse(DiffCallback.areContentsTheSame(model1, model2), "Different items report as the same!")

        movie2 = Movie(false, 1, "some new name", 1.23, true)
        model2 = MovieModel(movie2)
        assertFalse(DiffCallback.areContentsTheSame(model1, model2), "Different items report as the same!")

        movie2 = Movie(false, 1, "some name", 1.236, true)
        model2 = MovieModel(movie2)
        assertFalse(DiffCallback.areContentsTheSame(model1, model2), "Different items report as the same!")

        movie2 = Movie(false, 1, "some name", 1.23, false)
        model2 = MovieModel(movie2)
        assertFalse(DiffCallback.areContentsTheSame(model1, model2), "Different items report as the same!")
    }

    @Test
    fun verifyMovieListOptionsElseCoverage() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.fab)).check(matches(isDisplayed())).perform(click())
        onView(withId(R.id.empty)).check((matches(not(isDisplayed()))))
        onView(withId(R.id.clear_all)).check(matches(isDisplayed())).perform(click())
        onView(withId(R.id.empty)).check((matches(isDisplayed())))
        activityRule.activity.runOnUiThread {
            val menuItem = activityRule.activity.getMenu().add("Dummy Item")
            val fragment = activityRule.activity.movieListFragment
            fragment.onOptionsItemSelected(menuItem)
        }
    }
}