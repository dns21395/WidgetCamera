package com.example.denisgabyshev.widgetcamera.injection

import com.example.denisgabyshev.widgetcamera.App
import com.example.denisgabyshev.widgetcamera.MainActivity
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
}