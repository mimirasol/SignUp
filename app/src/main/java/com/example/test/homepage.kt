package com.example.test

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.widget.ToggleButton
import android.widget.TextView
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class homepage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_homepage)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val logoutButton = findViewById<Button>(R.id.logoutBtn)
        val galleryButton = findViewById<Button>(R.id.Gallerybtn)

        logoutButton.setOnClickListener {
            Toast.makeText(this, "Logging Out...", Toast.LENGTH_LONG).show()

            val loginPage = Intent(this, LogIn::class.java)
            startActivity(loginPage)
        }

        galleryButton.setOnClickListener {
            val galleryButton = Intent(this, Gallery::class.java)
            startActivity(galleryButton)
        }

        val toggle = findViewById<ToggleButton>(R.id.themeBtn)
        val rootLayout = findViewById<View>(R.id.main) // Your parent layout
        val bg = findViewById<View>(R.id.mainBg)
        val tab = findViewById<View>(R.id.whiteTab)
        val bar = findViewById<View>(R.id.bar)
        val tabText = findViewById<TextView>(R.id.tabText)

        toggle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // 🌙 Dark Mode
                rootLayout.setBackgroundColor(resources.getColor(R.color.darkblue))
                bg.setBackgroundColor(resources.getColor(R.color.lightblack))
                tab.setBackgroundColor(resources.getColor(R.color.lightblack))
                bar.setBackgroundColor(resources.getColor(R.color.white))
                tabText.setTextColor(resources.getColor(R.color.white))

            } else {
                // ☀️ Light Mode
                rootLayout.setBackgroundColor(resources.getColor(R.color.blue))
                bg.setBackgroundColor(resources.getColor(R.color.white))
                tab.setBackgroundColor(resources.getColor(R.color.white))
                bar.setBackgroundColor(resources.getColor(R.color.darkblue))
                tabText.setTextColor(resources.getColor(R.color.darkblue))
            }
        }

    }
}