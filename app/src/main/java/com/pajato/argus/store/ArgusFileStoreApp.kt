package com.pajato.argus.store

import android.app.Application
import com.pajato.argus.store.repo.MovieRepository
import com.pajato.argus.store.ui.movie.MovieMotor
import kotlinx.serialization.UnstableDefault
import org.koin.android.ext.android.startKoin
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

@UnstableDefault
class ArgusFileStoreApp: Application() {
    companion object {
        internal var filesDirPath = ""
    }

    private val koinModule = module {
        single { MovieRepository() }
        viewModel { MovieMotor(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        filesDirPath = filesDir.absolutePath
        startKoin(this, listOf(koinModule))
    }
}
