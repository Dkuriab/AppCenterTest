package com.appcentertesttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import com.microsoft.appcenter.distribute.Distribute
import java.lang.NullPointerException
import java.util.*

@Suppress("DIVISION_BY_ZERO")
class MainActivity : AppCompatActivity() {
    private lateinit var throwExceptionButton: Button
    private lateinit var checkableButton: Button
    private lateinit var crashButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        throwExceptionButton = findViewById(R.id.throw_exception_button)
        checkableButton = findViewById(R.id.trackable_button)
        crashButton = findViewById(R.id.crash_button)

        AppCenter.start(
            application,
            "41912893-03be-4eec-bea2-32fcf44eca4d",
            Analytics::class.java,
            Crashes::class.java,
            Distribute::class.java
        )
        setButtonListeners()
    }

    private fun setButtonListeners() {
        checkableButton.setOnClickListener {
            Analytics.trackEvent(
                "Trackable button pressed",
                mapOf(
                    "DisplayLanguage" to Locale.getDefault().displayLanguage,
                    "CurrentTime" to Calendar.getInstance().time.toString()
                )
            )
        }
        crashButton.setOnClickListener {
            Analytics.trackEvent("\"Break the app\" button pressed")
//            Crashes.generateTestCrash()
            1 / 0 /* throws ArithmeticalException */
        }
        throwExceptionButton.setOnClickListener {
            try {
                throw NullPointerException("Test exception")
            } catch (e: NullPointerException) {
                Crashes.trackError(e)
            }
        }
    }
}