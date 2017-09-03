package com.example.denisgabyshev.widgetcamera.main

import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import com.example.denisgabyshev.widgetcamera.R
import com.readystatesoftware.systembartint.SystemBarTintManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()


    }

    fun setupViews() {
        setBackground()
        transparentStatusBar()
    }

    fun setBackground() {
        background.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.background))
    }

    fun transparentStatusBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.statusBarColor = Color.TRANSPARENT

            val tintManager = SystemBarTintManager(this)

            // enable status bar tint
            tintManager.isStatusBarTintEnabled = true
            // enable navigation bar tint
            tintManager.setNavigationBarTintEnabled(true)
        }



    }







}
