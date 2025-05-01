package com.example.submissionaplikasiandroidsederhana

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var isGridView = false
    private lateinit var rvManhwa: RecyclerView
    private val list = ArrayList<Manhwa>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvManhwa = findViewById(R.id.rv_manhwa)
        rvManhwa.setHasFixedSize(true)

        list.addAll(getListManhwa())
        showRecyclerList()

        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        val recyclerView: RecyclerView = findViewById(R.id.rv_manhwa)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ListManhwaAdapter(list)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    private var isListView = true

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_toggle_view -> {
                isGridView = !isGridView

                if (isGridView) {
                    rvManhwa.layoutManager = GridLayoutManager(this, 2)
                    item.setIcon(R.drawable.baseline_grid_view_24)
                } else {
                    rvManhwa.layoutManager = LinearLayoutManager(this)
                    item.setIcon(R.drawable.baseline_view_list_24)
                }

                val animation = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_fall_down)
                rvManhwa.layoutAnimation = animation
                rvManhwa.scheduleLayoutAnimation()

                return true
            }

            R.id.action_profile -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListManhwa(): ArrayList<Manhwa> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataAuthor = resources.getStringArray(R.array.manhwa_authors)
        val dataGenre = resources.getStringArray(R.array.manhwa_genres)
        val dataPublished = resources.getStringArray(R.array.manhwa_published_years)

        val listManhwa = ArrayList<Manhwa>()
        for (i in dataName.indices){
            val manhwa = Manhwa(
                dataName[i],
                dataDescription[i],
                dataPhoto[i],
                dataAuthor[i],
                dataGenre[i],
                dataPublished[i]
            )
            listManhwa.add(manhwa)
        }
        return listManhwa
    }

    private fun showRecyclerList(){
        rvManhwa.layoutManager = LinearLayoutManager(this)
        val listManhwaAdapter = ListManhwaAdapter(list)
        rvManhwa.adapter = listManhwaAdapter
    }

    private fun switchLayout() {
        isGridView = !isGridView

        if (isGridView) {
            rvManhwa.layoutManager = GridLayoutManager(this, 2)
        } else {
            rvManhwa.layoutManager = LinearLayoutManager(this)
        }

        // Pasang animasi layout setiap ganti mode
        val controller = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_fall_down)
        rvManhwa.layoutAnimation = controller
        rvManhwa.adapter?.notifyDataSetChanged()
        rvManhwa.scheduleLayoutAnimation()
    }

}