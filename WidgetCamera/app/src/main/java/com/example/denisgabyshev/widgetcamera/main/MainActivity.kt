package com.example.denisgabyshev.widgetcamera.main

import android.Manifest
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.pm.PackageManager.*
import android.support.v4.app.ActivityCompat
import android.util.Log
import com.example.denisgabyshev.widgetcamera.R
import com.example.denisgabyshev.widgetcamera.main.permission.PermissionFragment
import com.example.denisgabyshev.widgetcamera.main.settings.SettingsFragment
import com.readystatesoftware.systembartint.SystemBarTintManager

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
    }

    private fun setupViews() {
        transparentStatusBar()
        requestPermission()
        setFragment()
    }

    private fun setFragment() {
        Log.d(TAG, "permission granted : ${checkPermissionsIsGranted()}")
        if(checkPermissionsIsGranted()) {
            Log.d(TAG, "settingsFragment()")
            supportFragmentManager.beginTransaction().replace(R.id.frameLayout, SettingsFragment()).commit()
        } else {
            Log.d(TAG, "permissionFragment()")
            supportFragmentManager.beginTransaction().replace(R.id.frameLayout, PermissionFragment()).commit()
        }
    }

    private fun requestPermission() {
        if(!checkPermissionsIsGranted()) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
                                                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                                            Manifest.permission.VIBRATE,
                                                            Manifest.permission.CAMERA), 1337)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        setFragment()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        setFragment()
    }

    private fun checkPermissionsIsGranted(): Boolean {
        if(Build.VERSION.SDK_INT >= 23) {
            return (permissionIsGranted(Manifest.permission.READ_EXTERNAL_STORAGE)  &&
                    permissionIsGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE) &&
                    permissionIsGranted(Manifest.permission.VIBRATE)                &&
                    permissionIsGranted(Manifest.permission.CAMERA))
        }
        return true
    }

    private fun permissionIsGranted(permission: String) : Boolean {
        if(Build.VERSION.SDK_INT >= 23) {
            return (checkSelfPermission(permission) == PERMISSION_GRANTED)
        }
        return true
    }


    private fun transparentStatusBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.statusBarColor = Color.TRANSPARENT

            val tintManager = SystemBarTintManager(this)

            // enable status bar tint
            tintManager.isStatusBarTintEnabled = true
            // enable navigation bar tint
            tintManager.setNavigationBarTintEnabled(true)
        }
    }







}
