package com.grupo15.vinilos.presentation.albumes

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grupo15.vinilos.R
import com.grupo15.vinilos.data.model.Album
import com.grupo15.vinilos.data.model.Collector

class AlbumAdapter (private var albums: List<Album>):
RecyclerView.Adapter<AlbumAdapter.ViewHolder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_album, viewGroup,  false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int){
        viewHolder.albumTitle.text = albums[position].name
    }

    override fun getItemCount(): Int = albums.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val albumTitle: TextView

        init {
            albumTitle = view.findViewById(R.id.album_title)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAlbums(albumes: List<Album>){
        this.albums = albumes
        notifyDataSetChanged()
    }
}