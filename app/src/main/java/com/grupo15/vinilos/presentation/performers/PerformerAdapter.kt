package com.grupo15.vinilos.presentation.performers

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grupo15.vinilos.R
import com.grupo15.vinilos.data.model.Performer

class PerformerAdapter (private var performers: List<Performer>):
    RecyclerView.Adapter<PerformerAdapter.ViewHolder>()  {


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_performer, viewGroup,  false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.performerTitle.text = performers[position].name
    }

    override fun getItemCount(): Int = performers.size


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val performerTitle: TextView

        init {
            performerTitle = view.findViewById(R.id.performer_title)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updatePerformers(performers: List<Performer>){
        this.performers = performers
        notifyDataSetChanged()
    }

}