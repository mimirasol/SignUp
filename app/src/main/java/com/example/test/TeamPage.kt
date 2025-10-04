package com.example.test

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.MediaController
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.SeekBar
import android.media.AudioManager

class TeamPage : AppCompatActivity() {
    private lateinit var audioManager: AudioManager
    private lateinit var volumeSeekBar: SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_team_page)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val videoPresentation = findViewById<VideoView>(R.id.teamVideo)
        videoPresentation.setVideoPath("android.resource://${packageName}/${R.raw.teamvideo}")

        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoPresentation)
        videoPresentation.setMediaController(mediaController)

        videoPresentation.start()

        val back = findViewById<ImageButton>(R.id.backButton)

        audioManager = getSystemService(AUDIO_SERVICE) as AudioManager
        volumeSeekBar = findViewById<SeekBar>(R.id.seekBar)

        val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        volumeSeekBar.max = maxVolume

        val currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        volumeSeekBar.progress = currentVolume

        volumeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        back.setOnClickListener {
            val gallery = Intent(this, Gallery::class.java)
            startActivity(gallery)
        }
    }
}
