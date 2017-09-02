package com.example.denisgabyshev.widgetcamera.injection

import com.example.denisgabyshev.widgetcamera.App
import com.example.denisgabyshev.widgetcamera.MainActivity
import com.example.denisgabyshev.widgetcamera.camera.AppCamera16
import com.example.denisgabyshev.widgetcamera.service.CameraService
import dagger.Component
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by denisgabyshev on 01/09/2017.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(app: App)
    fun inject(mainActivity: MainActivity)
    fun inject(cameraService: CameraService)
    fun inject(appCamera16: AppCamera16)
}