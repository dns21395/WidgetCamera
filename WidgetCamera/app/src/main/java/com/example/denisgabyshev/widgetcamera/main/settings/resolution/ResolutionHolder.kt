package com.example.denisgabyshev.widgetcamera.main.settings.resolution

import android.content.Context
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.denisgabyshev.widgetcamera.model.UserPreferences
import kotlinx.android.synthetic.main.holder_resolution.view.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.textColor

/**
 * Created by denisgabyshev on 03/09/2017.
 */
class ResolutionHolder(val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(resolutionValue: Resolution) = with(view) {
        resolution.text = "${resolutionValue.width}X${resolutionValue.height}"
    }

    fun setCurrentResolution(context: Context, value: () -> Boolean) = with(view) {
        when(value()) {
            true -> {
                holder.backgroundColor = ContextCompat.getColor(context, android.R.color.darker_gray)
                resolution.textColor = ContextCompat.getColor(context, android.R.color.black)
            }
            false -> {
                holder.backgroundColor = ContextCompat.getColor(context, android.R.color.white)
                resolution.textColor = ContextCompat.getColor(context, android.R.color.darker_gray)
            }
        }

    }
}