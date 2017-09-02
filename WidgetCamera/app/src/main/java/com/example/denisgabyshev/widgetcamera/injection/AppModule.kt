package com.example.denisgabyshev.widgetcamera.injection

import com.example.denisgabyshev.widgetcamera.App
import com.example.denisgabyshev.widgetcamera.camera.AppCamera16
import com.example.denisgabyshev.widgetcamera.model.UserPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by denisgabyshev on 01/09/2017.
 */
@Module class AppModule(val app: App) {
    @Provides @Singleton fun provideApp() = app

    @Provides @Singleton fun provideCamera() = AppCamera16(app)

    @Provides @Singleton fun providePrefernces() = UserPreferences(app)
}