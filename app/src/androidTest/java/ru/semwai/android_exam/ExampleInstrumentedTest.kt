package ru.semwai.android_exam

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ServiceTestRule
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.any

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("ru.semwai.android_exam", appContext.packageName)
    }

    @get:Rule
    val serviceRule = ServiceTestRule()

    @Test
    fun testService() {
        val serviceIntent = Intent(ApplicationProvider.getApplicationContext<Context>(), MyRandomService::class.java)

        val binder = serviceRule.bindService(serviceIntent)

        val service: MyRandomService = (binder as MyRandomService.LocalBinder).getService()

        assertThat(service.randomNumber, `is`(any(Int::class.java)))
    }

}