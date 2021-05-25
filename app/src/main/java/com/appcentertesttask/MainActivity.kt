package com.appcentertesttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import com.microsoft.appcenter.distribute.Distribute

@Suppress("DIVISION_BY_ZERO")
class MainActivity : AppCompatActivity() {

    lateinit var checkableButton: Button
    lateinit var crashButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCenter.start(
            application,
            "41912893-03be-4eec-bea2-32fcf44eca4d",
            Analytics::class.java,
            Crashes::class.java,
            Distribute::class.java
        )

        checkableButton = findViewById(R.id.checkable_button)
        crashButton = findViewById(R.id.crash_button)

        checkableButton.setOnClickListener {
            Analytics.trackEvent("TapMe button tapped")
        }
        crashButton.setOnClickListener {
            Analytics.trackEvent("Crash button tapped")
            Crashes.generateTestCrash()
        }
    }
}