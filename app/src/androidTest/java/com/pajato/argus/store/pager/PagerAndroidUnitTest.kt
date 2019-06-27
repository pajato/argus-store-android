package com.pajato.argus.store.pager

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals


@RunWith(AndroidJUnit4::class)
class PagerAndroidUnitTest {
    private val pager = Pager()

    @Test
    fun verifyFirstPageRequest() {
        val page = pager.getSomeMovies()
        assertEquals(25, page.size, "First page has wrong size!")
    }
}