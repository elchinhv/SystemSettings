package com.olcane.systemsettings

import android.app.Activity
import android.app.ActivityOptions
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast

// A plain framework Activity with a framework theme: no AndroidX or Material
// code is loaded, so a cold start of this app does as little as possible
// before handing over to the system Settings app.
class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openSystemSettings()
    }

    private fun openSystemSettings() {
        val intent = Intent(Settings.ACTION_SETTINGS)

        try {
            if (intent.resolveActivity(packageManager) != null) {
                val options = ActivityOptions.makeCustomAnimation(this, 0, 0)
                startActivity(intent, options.toBundle())
            } else {
                showErrorToast()
            }
        } catch (_: ActivityNotFoundException) {
            // Catches cases where the OS reported a resolver but failed to launch
            showErrorToast()
        } catch (_: SecurityException) {
            // Catches edge cases where MDM or user profiles block the settings intent
            showErrorToast()
        } catch (_: Exception) {
            // Fallback for any other unexpected runtime crashes
            showErrorToast()
        } finally {
            // Ensure the invisible activity finishes regardless of the outcome
            finish()
        }
    }

    private fun showErrorToast() {
        // Application context: the toast outlives this activity, which finishes at once.
        Toast.makeText(applicationContext, R.string.failed_to_open_system_settings, Toast.LENGTH_LONG).show()
    }
}
