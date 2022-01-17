package ru.semwai.android_exam

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.util.*

class MyRandomService : Service() {

    private val binder = LocalBinder()

    private val mGenerator = Random()

    val randomNumber: Int
        get() = mGenerator.nextInt(100)

    inner class LocalBinder : Binder() {
        // Return this instance of LocalService so clients can call public methods
        fun getService(): MyRandomService = this@MyRandomService
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }
}