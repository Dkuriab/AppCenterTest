package com.appcentertesttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.annotation.MainThread
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import com.microsoft.appcenter.distribute.Distribute
import java.lang.NullPointerException
import java.util.*

@Suppress("DIVISION_BY_ZERO")
class MainActivity : AppCompatActivity() {
    private lateinit var trackEventButton: Button
    private lateinit var trackErrorButton: Button
    private lateinit var crashTheAppButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trackEventButton = findViewById(R.id.track_event_button)
        trackErrorButton = findViewById(R.id.track_error_button)
        crashTheAppButton = findViewById(R.id.crash_the_app_button)

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
        trackEventButton.setOnClickListener {
            Analytics.trackEvent(
                "\"TrackEvent\" button pressed",
                mapOf(
                    "DisplayLanguage" to Locale.getDefault().displayLanguage,
                    "CurrentTime" to Calendar.getInstance().time.toString()
                )
            )
        }
        crashTheAppButton.setOnClickListener {
            suspend {
                Analytics.trackEvent("\"CrashTheApp\" button pressed")
            }
//            Crashes.generateTestCrash()
            1 / 0 /* throws ArithmeticalException */
        }
        trackErrorButton.setOnClickListener {
            try {
                throw NullPointerException("Test exception for TrackErrorButton")
            } catch (e: NullPointerException) {
                Analytics.trackEvent("\"TrackError\" button pressed")
                Crashes.trackError(e)
            }
        }
    }
}