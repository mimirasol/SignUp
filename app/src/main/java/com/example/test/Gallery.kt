package com.example.test

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button

class Gallery : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_gallery)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val miraPage = findViewById<ImageView>(R.id.mira_gallery)
        val nathanPage = findViewById<ImageView>(R.id.nathan_gallery)
        val samPage = findViewById<ImageView>(R.id.sam_gallery)
        val jeffPage = findViewById<ImageView>(R.id.jeff_gallery)
        val extraPage = findViewById<ImageView>(R.id.extra_gallery)
        val extraPage2 = findViewById<ImageView>(R.id.extra2_gallery)
        val returnDashboard = findViewById<Button>(R.id.return_dashboard)

        returnDashboard.setOnClickListener {
            val backDashboard = Intent(this, homepage::class.java)
            startActivity(backDashboard)
        }

        miraPage.setOnClickListener {
            val miraDesc = Intent(this, MiraDescription::class.java)
            startActivity(miraDesc)
        }
        nathanPage.setOnClickListener {
            val nathanDesc = Intent(this, NathanDescription::class.java)
            startActivity(nathanDesc)
        }
        samPage.setOnClickListener {
            val samPage = Intent(this, SamDescription::class.java)
            startActivity(samPage)
        }
        jeffPage.setOnClickListener {
            val jeffPage = Intent(this, JepDescription::class.java)
            startActivity(jeffPage)
        }
        extraPage.setOnClickListener {
            val extraPage = Intent(this, TeamPage::class.java)
            startActivity(extraPage)
        }
        extraPage2.setOnClickListener {
            val extraPage2 = Intent(this, TeamPage::class.java)
            startActivity(extraPage2)
        }
    }
}