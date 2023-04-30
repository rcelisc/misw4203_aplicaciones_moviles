package com.grupo15.vinilos.presentation.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.grupo15.vinilos.databinding.FragmentAlbumBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumFragment : Fragment() {

    private var _binding: FragmentAlbumBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val albumsViewModel =
            ViewModelProvider(this).get(AlbumViewModel::class.java)

        _binding = FragmentAlbumBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val albumRecycler:RecyclerView=binding.albumRecycle
        albumRecycler.adapter=AlbumAdapter(emptyList())

        albumsViewModel.albums.observe(viewLifecycleOwner){ album->
            val adapter = albumRecycler.adapter as AlbumAdapter
            adapter.updateAlbums(album)
        }

        albumsViewModel.error.observe(viewLifecycleOwner){message->
            Toast.makeText(context,message, Toast.LENGTH_LONG).show()
        }
        albumsViewModel.getAlbums()
        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
