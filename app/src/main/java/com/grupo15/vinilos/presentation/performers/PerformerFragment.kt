package com.grupo15.vinilos.presentation.performers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.grupo15.vinilos.databinding.FragmentPerformerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PerformerFragment : Fragment() {

    private var _binding: FragmentPerformerBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val performerViewModel =
            ViewModelProvider(this).get(PerformerViewModel::class.java)

        _binding = FragmentPerformerBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val performerRecycler: RecyclerView = binding.performerRecycler
        performerRecycler.adapter = PerformerAdapter(emptyList())


        performerViewModel.performers.observe(viewLifecycleOwner) { performer ->
            val adapter = performerRecycler.adapter as PerformerAdapter
            adapter.updatePerformers(performer)
        }

        performerViewModel.error.observe(viewLifecycleOwner) { message ->
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }

        performerViewModel.getPerformers()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
