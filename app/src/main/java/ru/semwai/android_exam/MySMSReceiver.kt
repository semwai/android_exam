package ru.semwai.android_exam

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.provider.Telephony
import android.telephony.SmsMessage
import android.util.Log

class MySMSReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        
        if (intent.action.equals("android.provider.Telephony.SMS_RECEIVED")) {
            val messages = Telephony.Sms.Intents.getMessagesFromIntent(intent)
            for (text in messages)
                Log.v("BROADCAST", "NEW SMS: ${text.messageBody}")
        }
    }
}