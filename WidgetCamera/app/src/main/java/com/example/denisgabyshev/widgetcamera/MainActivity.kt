package com.example.denisgabyshev.widgetcamera

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.denisgabyshev.widgetcamera.camera.AppCamera
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var camera: AppCamera

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (applicationContext as App).component.inject(this)
    }


}
