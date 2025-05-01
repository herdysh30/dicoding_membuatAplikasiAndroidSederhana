package com.example.submissionaplikasiandroidsederhana

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListManhwaAdapter(private val listManhwa: ArrayList<Manhwa>) : RecyclerView.Adapter<ListManhwaAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvname: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_manhwa, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listManhwa.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val manhwa = listManhwa[position]
        Glide.with(holder.itemView.context)
            .load(manhwa.photo)
            .into(holder.imgPhoto)
        holder.tvname.text = manhwa.name
        holder.tvDescription.text = manhwa.description
        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra(DetailActivity.EXTRA_MANHWA, manhwa)
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: Manhwa)
    }
}