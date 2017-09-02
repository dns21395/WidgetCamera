package com.example.denisgabyshev.widgetcamera.model

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * Created by Borya on 02.09.2017.
 */
class UserPreferences(val context: Context) {
    val editor = PreferenceManager.getDefaultSharedPreferences(context)

    private val HEIGHT = "height"
    private val WIDTH = "width"

    fun setInt(key: String, value: Int)
            = editor.edit().putInt(key, value).apply()

    fun getInt(key: String, initial: Int): Int
            = editor.getInt(key, initial)

    fun setWidth(value: Int) = setInt(WIDTH, value)
    fun setHeight(value: Int) = setInt(HEIGHT, value)

    fun getWidth() = getInt(WIDTH, 1920)
    fun getHeight() = getInt(HEIGHT, 1080)




}