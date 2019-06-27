package com.pajato.argus.store

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.pajato.argus.store.ui.AboutActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AboutActivityTest {
    @get:Rule val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField val activityRule: ActivityTestRule<AboutActivity> = ActivityTestRule(AboutActivity::class.java)

    @Test
    fun verifyWebViewIsDisplaye() {
        ActivityScenario.launch(AboutActivity::class.java)
        onView(withId(R.id.about)).check((matches(isDisplayed())))
    }
}