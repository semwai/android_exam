package ru.semwai.android_exam

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.util.Log

private const val TAG = "BROADCAST"

class MyBatteryReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        //TODO("MyBatteryReceiver.onReceive() is not implemented")
        val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)
        Log.v(TAG, "battery: $level %")
    }
}