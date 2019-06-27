package com.pajato.argus.store.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pajato.argus.store.R
import kotlinx.android.synthetic.main.activity_about.about

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        about.loadUrl("file:///android_asset/about.html")
    }
}
