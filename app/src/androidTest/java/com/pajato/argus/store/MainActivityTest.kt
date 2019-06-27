package com.pajato.argus.store

import android.content.pm.ActivityInfo
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.ActivityTestRule
import com.pajato.argus.store.ui.MainActivity
import kotlinx.serialization.UnstableDefault
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@UnstableDefault
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule @JvmField val activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun verifyFabCanBeClicked() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.fab)).perform(click())
    }

    @Test
    fun verifyOptionsBasic() {
        ActivityScenario.launch(MainActivity::class.java)
        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
        onView(withText(R.string.menuAbout)).perform(click())
    }

    @Test
    fun verifyOptionsElseCoverage() {
        ActivityScenario.launch(MainActivity::class.java)
        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
        activityRule.activity.runOnUiThread {
            val menuItem = activityRule.activity.getMenu().add("Dummy Item")
            activityRule.activity.onOptionsItemSelected(menuItem)
        }
    }
}
