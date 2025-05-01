package com.example.submissionaplikasiandroidsederhana

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.appbar.MaterialToolbar

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        supportActionBar?.title = "About Me"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val imgProfile = findViewById<ImageView>(R.id.img_profile)
        val tvName = findViewById<TextView>(R.id.tv_name)
        val tvEmail = findViewById<TextView>(R.id.tv_email)

        Glide.with(this)
            .load(R.drawable.foto_diri)
            .circleCrop()
            .into(imgProfile)

        tvName.text = "Herdy Sya'banul Hakim"
        tvEmail.text = "herdy.syaban@gmail.com"

        val toolbar: MaterialToolbar = findViewById(R.id.topAppBar)
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}