package com.esp8266.gamecontroller

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(40, 60, 40, 40)
        }

        val title = TextView(this).apply {
            text = "ESP8266 Wi-Fi Game Controller"
            textSize = 22f
        }

        val ipBox = EditText(this).apply {
            hint = "ESP8266 IP Address"
        }

        val saveButton = Button(this).apply {
            text = "SAVE ESP IP"
        }

        val enableButton = Button(this).apply {
            text = "ENABLE CONTROLLER"
        }

        val status = TextView(this).apply {
            text = "Controller Ready"
            textSize = 18f
        }

        val prefs = getSharedPreferences("controller", MODE_PRIVATE)

        ipBox.setText(
            prefs.getString("esp_ip", "192.168.1.9")
        )

        saveButton.setOnClickListener {
            val ip = ipBox.text.toString().trim()

            prefs.edit()
                .putString("esp_ip", ip)
                .apply()

            status.text = "Saved: $ip"
        }

        enableButton.setOnClickListener {
            startActivity(
                Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            )
        }

        layout.addView(title)
        layout.addView(ipBox)
        layout.addView(saveButton)
        layout.addView(enableButton)
        layout.addView(status)

        setContentView(layout)
    }
}
