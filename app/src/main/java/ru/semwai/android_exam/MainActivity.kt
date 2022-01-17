package ru.semwai.android_exam

import android.content.*
import android.content.pm.PackageManager
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.provider.Telephony
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.util.jar.Manifest

private const val SMS_REQUEST = 1

class MainActivity : AppCompatActivity() {
    private lateinit var mService: MyRandomService
    private var mBound: Boolean = false

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binder = service as MyRandomService.LocalBinder
            mService = binder.getService()
            mBound = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            mBound = false
        }
    }

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

        val textView = findViewById<TextView>(R.id.textView)
        textView.setOnClickListener {
            if (mBound) {
                val num = mService.randomNumber
                textView.text = "random number: $num"
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Intent(this, MyRandomService::class.java).also {
            bindService(it, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
        mBound = false
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