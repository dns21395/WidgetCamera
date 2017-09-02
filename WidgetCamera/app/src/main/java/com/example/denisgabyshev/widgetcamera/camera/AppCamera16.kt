package com.example.denisgabyshev.widgetcamera.camera

import android.content.Context
import android.graphics.SurfaceTexture
import android.hardware.Camera
import android.util.Log
import com.example.denisgabyshev.widgetcamera.App
import com.example.denisgabyshev.widgetcamera.model.UserPreferences
import org.jetbrains.anko.doAsync
import javax.inject.Inject
import android.os.Environment
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by denisgabyshev on 01/09/2017.
 */
class AppCamera16(context: Context)  {
    private val TAG = "Camera16"

    @Inject lateinit var preferences: UserPreferences

    init {
        (context.applicationContext as App).component.inject(this)
    }

    private var camera: Camera? = null
    private var cameraParameters: Camera.Parameters? = null

    private var surfaceTexture: SurfaceTexture? = null

    fun setupCamera() {
        camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK)

        surfaceTexture = SurfaceTexture(0)
        cameraParameters = camera?.parameters
        cameraParameters?.setPictureSize(preferences.getWidth(), preferences.getHeight())
        cameraParameters?.setRotation(90)
        camera?.parameters = cameraParameters
    }

    fun takePicture() {
        doAsync {
            camera?.setPreviewTexture(surfaceTexture)
            camera?.startPreview()
            camera?.autoFocus { p0, p1 -> camera?.takePicture(null, null, pictureCallback) }
        }
    }

    private val pictureCallback = Camera.PictureCallback { p0, p1 ->
        val pictureFile = getOutputMediaFile()

        if(pictureFile == null) {
            Log.e(TAG, "Error creating media file, check storage permission")
            return@PictureCallback
        }

        val fos = FileOutputStream(pictureFile)
        fos.write(p0)
        fos.close()
    }

    private fun getOutputMediaFile(): File? {
        val mediaStorageDir = File(Environment.getExternalStorageDirectory(), "WidgetCamera")

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.i(TAG, "Failed to create directory")
                return null
            }
        }

        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val mediaFile = File("${mediaStorageDir.path}${File.separator}IMG_$timeStamp.jpg")
        return mediaFile
    }

    fun destroyCamera() {
        camera?.stopPreview()
        camera?.release()
        camera = null
    }


}