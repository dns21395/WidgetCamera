package com.example.denisgabyshev.widgetcamera.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.denisgabyshev.widgetcamera.App
import com.example.denisgabyshev.widgetcamera.camera.AppCamera16
import javax.inject.Inject

/**
 * Created by Borya on 02.09.2017.
 */
class CameraService : Service() {

    @Inject lateinit var camera: AppCamera16

    override fun onCreate() {
        super.onCreate()
        (applicationContext as App).component.inject(this)


    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}