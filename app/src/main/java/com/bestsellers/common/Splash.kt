package com.bestsellers.common

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bestsellers.R
import com.bestsellers.main.MainActivity
import com.bestsellers.util.launchActivity
import kotlinx.android.synthetic.main.activity_splash.*


class Splash : AppCompatActivity() {

    private val duration = 2000
    private val delay = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        configurePathView()
    }

    private fun configurePathView() {
        pathView.pathAnimator.apply {
            delay(delay)
            duration(duration)
            listenerEnd { initGenreActivity()}
            start()
        }
    }

    private fun initGenreActivity() {
        finish()
        launchActivity<MainActivity>()
    }
}
