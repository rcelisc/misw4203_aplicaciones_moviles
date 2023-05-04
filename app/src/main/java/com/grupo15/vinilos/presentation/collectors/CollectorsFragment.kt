package com.grupo15.vinilos.presentation.collectors

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.grupo15.vinilos.data.model.Collector
import com.grupo15.vinilos.databinding.FragmentCollectorsBinding
import com.grupo15.vinilos.presentation.collectors.detail.CollectorDetailActivity
import com.grupo15.vinilos.presentation.collectors.detail.CollectorDetailActivity.Companion.COLLECTOR_ID_KEY
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels

@AndroidEntryPoint
class CollectorsFragment : Fragment(), OnCollectorClickListener {

    private var _binding: FragmentCollectorsBinding? = null

    private val binding get() = _binding!!

    private val collectorsViewModel: CollectorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCollectorsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val collectorRecycler: RecyclerView = binding.collectorRecycle
        collectorRecycler.adapter = CollectorAdapter(emptyList(), this)

        collectorsViewModel.collectors.observe(viewLifecycleOwner){ collector ->
            val adapter = collectorRecycler.adapter as CollectorAdapter
            adapter.updateCollectors(collector)
        }

        collectorsViewModel.error.observe(viewLifecycleOwner) { message ->
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }

        collectorsViewModel.getCollectors()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCollectorClick(collector: Collector) {
        val intent = Intent(requireContext(), CollectorDetailActivity::class.java)
        intent.putExtra(COLLECTOR_ID_KEY, collector.id.toString())
        startActivity(intent)
    }

}
