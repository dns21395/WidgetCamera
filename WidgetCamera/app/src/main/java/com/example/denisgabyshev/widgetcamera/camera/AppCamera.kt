package com.example.denisgabyshev.widgetcamera.camera

import android.content.Context
import android.graphics.SurfaceTexture
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager

/**
 * Created by denisgabyshev on 01/09/2017.
 */
class AppCamera(val context: Context) : CameraHelper {
    private var cameraManager: CameraManager? = null
    private var camera: CameraCharacteristics? = null

    override fun initialization() {
        cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
    }

    override fun getCameraIds(): Array<String>? {
        try {
            return cameraManager?.cameraIdList
        } catch (e: CameraAccessException) {
            return null
        }
    }

    override fun getBackCameraId(cameraIds: Array<String>): String? {
        for(camera in cameraIds) {
            val cameraCharacteristics = cameraManager?.getCameraCharacteristics(camera) ?: throw NullPointerException("No camera with id $camera")
            if(cameraCharacteristics.get(CameraCharacteristics.LENS_FACING) == CameraCharacteristics.LENS_FACING_BACK) {
                return camera
            }
        }
        return null
    }

    override fun setCamera(cameraId: String) {
        camera = cameraManager?.getCameraCharacteristics(cameraId)
    }

    override fun releaseCamera() {

    }




}