package com.grupo15.vinilos.presentation.collectors

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grupo15.vinilos.R
import com.grupo15.vinilos.data.model.Collector

class CollectorAdapter(
    private var collectors: List<Collector>,
    private val listener: OnCollectorClickListener
) :
    RecyclerView.Adapter<CollectorAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_collector, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.collectorTitle.text = collectors[position].name
        viewHolder.itemView.setOnClickListener {
            listener.onCollectorClick(collectors[position])
        }
    }

    override fun getItemCount(): Int = collectors.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val collectorTitle: TextView

        init {
            collectorTitle = view.findViewById(R.id.collector_title)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCollectors(collectors: List<Collector>) {
        this.collectors = collectors
        notifyDataSetChanged()
    }

}

interface OnCollectorClickListener {
    fun onCollectorClick(collector: Collector)

}
