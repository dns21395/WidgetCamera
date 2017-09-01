package com.example.denisgabyshev.widgetcamera.camera

import android.hardware.camera2.CameraCharacteristics

/**
 * Created by denisgabyshev on 01/09/2017.
 */
interface CameraHelper {
    fun initialization()

    fun getCameraIds() : Array<String>?

    fun getBackCameraId(cameraIds: Array<String>) : String?

    fun setCamera(cameraId: String)

    fun releaseCamera()
}