package com.example.denisgabyshev.widgetcamera.main.settings.resolution

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.holder_resolution.view.*

/**
 * Created by denisgabyshev on 03/09/2017.
 */
class ResolutionHolder(val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(resolutionValue: Resolution) = with(view) {
        resolution.text = "${resolutionValue.width}X${resolutionValue.height}"
    }
}