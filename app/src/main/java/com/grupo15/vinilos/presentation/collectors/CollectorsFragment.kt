package com.grupo15.vinilos.presentation.collectors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.grupo15.vinilos.databinding.FragmentCollectorsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CollectorsFragment : Fragment() {

    private var _binding: FragmentCollectorsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val collectorsViewModel =
            ViewModelProvider(this).get(CollectorViewModel::class.java)

        _binding = FragmentCollectorsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val collectorRecycler: RecyclerView = binding.collectorRecycle
        collectorRecycler.adapter = CollectorAdapter(emptyList())

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
}
