package com.example.denisgabyshev.widgetcamera.main.settings.resolution

import android.content.Context
import android.hardware.Camera
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.denisgabyshev.widgetcamera.App
import com.example.denisgabyshev.widgetcamera.R
import com.example.denisgabyshev.widgetcamera.model.UserPreferences
import javax.inject.Inject

/**
 * Created by denisgabyshev on 03/09/2017.
 */
class ResolutionAdapter(val context: Context, val resolutions: ArrayList<Resolution>) : RecyclerView.Adapter<ResolutionHolder>() {

    @Inject lateinit var preferences: UserPreferences

    init {
        (context.applicationContext as App).component.inject(this)
    }

    override fun onBindViewHolder(holder: ResolutionHolder, position: Int) {
        val resolution = resolutions[position]

        holder.bind(resolutions[position])

        holder.setCurrentResolution(context, {
            (resolution.width == preferences.getWidth() && resolution.height == preferences.getHeight())
        })

        holder.view.setOnClickListener {
            preferences.setWidth(resolution.width)
            preferences.setHeight(resolution.height)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ResolutionHolder
        = ResolutionHolder(LayoutInflater.from(context).inflate(R.layout.holder_resolution, parent, false))




    override fun getItemCount(): Int = resolutions.size

}