package com.pajato.argus.store.repo

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pajato.argus.store.ArgusFileStoreApp
import com.pajato.argus.store.ui.MainActivity
import kotlinx.serialization.UnstableDefault
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertTrue

@UnstableDefault
@RunWith(AndroidJUnit4::class)
class RepoTest {

    @Test
    fun setFilesDirPathForCoverage() {
        ActivityScenario.launch(MainActivity::class.java)
        ArgusFileStoreApp.filesDirPath = "some string"
    }
}