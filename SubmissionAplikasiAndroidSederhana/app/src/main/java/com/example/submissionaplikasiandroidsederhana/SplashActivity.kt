package com.example.submissionaplikasiandroidsederhana

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val splashTitle = findViewById<TextView>(R.id.tv_splash_title)

        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        splashTitle.startAnimation(fadeIn)

        val zoomIn = AnimationUtils.loadAnimation(this, R.anim.zoom_in)
        splashTitle.startAnimation(zoomIn)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 2000)
    }
}
