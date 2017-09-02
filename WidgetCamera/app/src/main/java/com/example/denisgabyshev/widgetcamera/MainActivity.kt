package com.example.denisgabyshev.widgetcamera

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.denisgabyshev.widgetcamera.camera.AppCamera16
import com.example.denisgabyshev.widgetcamera.service.CameraService
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var camera: AppCamera16

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (applicationContext as App).component.inject(this)



        service.setOnClickListener {
            startService(Intent(this, CameraService::class.java))
        }

        picture.setOnClickListener {
            camera.takePicture()
        }


    }


}
