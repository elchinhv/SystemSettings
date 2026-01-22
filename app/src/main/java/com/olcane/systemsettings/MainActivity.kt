package com.olcane.systemsettings

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openSystemSettings()
    }

    private fun openSystemSettings() {
        val intent = Intent(Settings.ACTION_SETTINGS)

        if (intent.resolveActivity(packageManager) != null) {
            val options = ActivityOptions.makeCustomAnimation(this, 0, 0)
            startActivity(intent, options.toBundle())
        } else {
            Toast.makeText(this, R.string.failed_to_open_system_settings, Toast.LENGTH_LONG).show()
        }

        finish()
    }
}