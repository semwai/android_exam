package ru.semwai.android_exam

import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import android.util.Log
import java.util.jar.Manifest

private const val SMS_REQUEST = 1

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val br = MyBatteryReceiver()
        val bFilter = IntentFilter(BatteryManager.EXTRA_LEVEL).apply {
            addAction(Intent.ACTION_BATTERY_CHANGED)
        }
        registerReceiver(br, bFilter)

        requestPermissions(arrayOf("android.permission.RECEIVE_SMS", "android.permission.READ_SMS"), SMS_REQUEST)

        val sr = MySMSReceiver()
        val sFilter = IntentFilter(Telephony.Sms.STATUS).apply {
            addAction("android.provider.Telephony.SMS_RECEIVED")
        }
        registerReceiver(sr, sFilter)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == SMS_REQUEST) {
            Log.v("PERMISSION", "SMS")
        }
    }
}