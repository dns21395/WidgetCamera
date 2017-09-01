package com.example.denisgabyshev.widgetcamera

import android.app.Application
import com.example.denisgabyshev.widgetcamera.injection.AppComponent
import com.example.denisgabyshev.widgetcamera.injection.AppModule
import com.example.denisgabyshev.widgetcamera.injection.DaggerAppComponent

/**
 * Created by denisgabyshev on 01/09/2017.
 */
class App : Application() {
    val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}