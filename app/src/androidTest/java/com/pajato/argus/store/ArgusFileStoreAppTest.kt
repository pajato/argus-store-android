package com.pajato.argus.store

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pajato.argus.store.ui.MainActivity
import kotlinx.serialization.UnstableDefault
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertTrue

@UnstableDefault
@RunWith(AndroidJUnit4::class)
class ArgusFileStoreAppTest {

    @Test
    fun verifyStorePathExists() {
        ActivityScenario.launch(MainActivity::class.java)
        val dirState = ArgusFileStoreApp.filesDirPath.isNotEmpty()
        assertTrue(dirState, "Invalid path for ArgusFileStoreApp!")
    }
}