package com.example.denisgabyshev.widgetcamera.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.example.denisgabyshev.widgetcamera.App
import com.example.denisgabyshev.widgetcamera.R
import com.example.denisgabyshev.widgetcamera.rx.RxBus
import com.example.denisgabyshev.widgetcamera.service.CameraService
import com.example.denisgabyshev.widgetcamera.service.ServiceControl
import javax.inject.Inject
import android.os.Vibrator
import android.util.Log


/**
 * Created by Borya on 02.09.2017.
 */
class AppWidget : AppWidgetProvider() {

    private val TAG = "CameraWidget"
    private val CAMERA = "camera"

    @Inject lateinit var rx: RxBus

    override fun onUpdate(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)

        val remoteViews = RemoteViews(context?.packageName, R.layout.widget)
        val watchWidget = ComponentName(context, AppWidget::class.java)

        remoteViews.setOnClickPendingIntent(R.id.widgetParent, getPendingIntent(context, CAMERA))
        appWidgetManager?.updateAppWidget(watchWidget, remoteViews)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)

        (context?.applicationContext as App).component.inject(this)

        Log.d(TAG, "onReceive action : ${intent?.action}")

        if(CAMERA.equals(intent?.action, true)) {
            if (!CameraService.isRunning(context, CameraService::class.java)) {
                context.startService(Intent(context, CameraService::class.java))
            } else {
                rx.send(ServiceControl.DESTROY)
            }
            vibrate(context)
            updateWidget(context)
        }



    }

    private fun getPendingIntent(context: Context?, action: String): PendingIntent {
        val intent = Intent(context, javaClass)
        intent.action = action
        return PendingIntent.getBroadcast(context, 0, intent, 0)
    }

    private fun updateWidget(context: Context?) {
        val intent = Intent(context, AppWidget::class.java)
        intent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
        val ids = AppWidgetManager.getInstance(context).getAppWidgetIds(ComponentName(context, AppWidget::class.java))
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
        context?.sendBroadcast(intent)

    }

    private fun vibrate(context: Context?) {
        val v = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        v.vibrate(100)
    }

}