package com.example.submissionaplikasiandroidsederhana

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.appbar.MaterialToolbar

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MANHWA = "key_manhwa"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val imgPhoto: ImageView = findViewById(R.id.img_item_photo)
        val tvName: TextView = findViewById(R.id.tv_item_name)
        val tvDescription: TextView = findViewById(R.id.tv_item_description)
        val tvAuthor: TextView = findViewById(R.id.text_author)
        val tvGenre: TextView = findViewById(R.id.text_genre)
        val tvPublished: TextView = findViewById(R.id.text_published)

        val manhwa = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_MANHWA, Manhwa::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_MANHWA)
        }

        manhwa?.let {
            Glide.with(this)
                .load(it.photo)
                .into(imgPhoto)
            tvName.text = it.name
            tvDescription.text = it.description
            tvAuthor.text = it.author
            tvGenre.text = it.genre
            tvPublished.text = it.published
        }

        val toolbar: MaterialToolbar = findViewById(R.id.topAppBar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {
                shareManhwa()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun shareManhwa() {
        val manhwa = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_MANHWA, Manhwa::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_MANHWA)
        }

        manhwa?.let {
            val shareText = "Yuk baca manhwa \"${it.name}\"!\nGenre: ${it.genre}\nAuthor: ${it.author}"
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, shareText)
            }
            startActivity(Intent.createChooser(intent, "Bagikan manhwa lewat..."))
        }
    }


}

