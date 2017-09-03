package com.example.denisgabyshev.widgetcamera.main.settings.resolution

import android.content.Context
import android.hardware.Camera
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.denisgabyshev.widgetcamera.R

/**
 * Created by denisgabyshev on 03/09/2017.
 */
class ResolutionAdapter(val context: Context, val resolutions: ArrayList<Resolution>) : RecyclerView.Adapter<ResolutionHolder>() {
    override fun onBindViewHolder(holder: ResolutionHolder, position: Int) {
        holder.bind(resolutions[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ResolutionHolder
        = ResolutionHolder(LayoutInflater.from(context).inflate(R.layout.holder_resolution, parent, false))

    override fun getItemCount(): Int = resolutions.size

}