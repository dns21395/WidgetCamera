package com.example.denisgabyshev.widgetcamera.service

import android.app.ActivityManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.example.denisgabyshev.widgetcamera.App
import com.example.denisgabyshev.widgetcamera.R
import com.example.denisgabyshev.widgetcamera.camera.AppCamera16
import com.example.denisgabyshev.widgetcamera.rx.RxBus
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule

/**
 * Created by Borya on 02.09.2017.
 */
class CameraService : Service() {
    companion object {
        fun isRunning(context: Context?, serviceClass: Class<*>): Boolean {
            val manager = context?.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            return manager.getRunningServices(Integer.MAX_VALUE).any { serviceClass.name == it.service.className }
        }
    }

    private val TAG = "CameraService"

    private var pictureCount = 0

    @Inject lateinit var rx: RxBus
    @Inject lateinit var camera: AppCamera16

    private val subscriptions = CompositeDisposable()

    override fun onCreate() {
        super.onCreate()

        (applicationContext as App).component.inject(this)

        subscribe()

        showNotification(pictureCount)

        camera.setupCamera()
    }

    private fun subscribe() {
        subscriptions.add(
                rx.toObservable()
                        .subscribe{
                            if(it is ServiceControl && it == ServiceControl.DESTROY) {
                                stopSelf()
                            }
                            if(it is ServiceControl && it == ServiceControl.TAKE_PICTURE) {
                                val timer = Timer("schedule", true)
                                timer.schedule(1000) {
                                    camera.takePicture()
                                    showNotification(++pictureCount)
                                }
                            }
                        }
        )

    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        stopForeground(true)
        subscriptions.clear()
        camera.destroyCamera()
        super.onDestroy()
    }

    private fun showNotification(pictureCount: Int) {
        val notification = NotificationCompat.Builder(applicationContext)
                .setSmallIcon(R.drawable.ic_photo_camera_white_24dp)
                .setContentTitle(resources.getString(R.string.app_name))
                .setContentText("Picture count : ${pictureCount}")
                .build()

        startForeground(1337, notification)
    }
}

