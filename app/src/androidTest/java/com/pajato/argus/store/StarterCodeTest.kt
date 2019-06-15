package com.pajato.argus.store

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class StarterCodeTest {
    @get:Rule val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule @JvmField val activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun verifyStarterCode() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.helloWorld)).check((matches(isDisplayed())))

        onView(withId(R.id.fab)).perform(click())

        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
        onView(withText(R.string.action_settings)).perform(click())

        val title = "Dummy Item"
        activityRule.activity.runOnUiThread {
            val item = activityRule.activity.getMenu().add(title)
            activityRule.activity.onOptionsItemSelected(item)
        }

        activityRule.finishActivity()
    }
}
