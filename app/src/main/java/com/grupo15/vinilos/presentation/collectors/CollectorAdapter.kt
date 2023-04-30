package com.grupo15.vinilos.presentation.collectors

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grupo15.vinilos.R
import com.grupo15.vinilos.data.model.Collector

class CollectorAdapter (private var collectorss: List<Collector>):
        RecyclerView.Adapter<CollectorAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_collector, viewGroup,  false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int){
        viewHolder.collectorTitle.text = collectorss[position].name
    }

    override fun getItemCount(): Int = collectorss.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val collectorTitle: TextView

        init {
            collectorTitle = view.findViewById(R.id.collector_title)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCollectors(collectors: List<Collector>){
        this.collectorss = collectors
        notifyDataSetChanged()
    }


}
