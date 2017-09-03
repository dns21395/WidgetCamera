package com.example.denisgabyshev.widgetcamera.main.settings

import android.hardware.Camera
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.denisgabyshev.widgetcamera.R
import com.example.denisgabyshev.widgetcamera.main.settings.resolution.Resolution
import com.example.denisgabyshev.widgetcamera.main.settings.resolution.ResolutionAdapter
import kotlinx.android.synthetic.main.fragment_settings.*
import org.jetbrains.anko.support.v4.toast

/**
 * Created by denisgabyshev on 03/09/2017.
 */
class SettingsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
        = inflater.inflate(R.layout.fragment_settings, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = ResolutionAdapter(context, getCameraAvailableResolutions())
    }



    private fun getCameraAvailableResolutions(): ArrayList<Resolution> {
        var camera : Camera? = null

        if(Camera.getNumberOfCameras() >= 2) {
            camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK)
        } else {
            camera = Camera.open(0)
        }

        val parameters = camera.parameters

        val resolutions = ArrayList<Resolution>()
        for(resolution in parameters.supportedPictureSizes) {
            resolutions.add(Resolution(resolution.width, resolution.height))
        }

        return resolutions
    }

    private val cameraErrorCallback = Camera.ErrorCallback { i, camera ->
        toast(R.string.another_app)
    }




}