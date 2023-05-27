package com.grupo15.vinilos.presentation.albums

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.grupo15.vinilos.data.model.Album
import com.grupo15.vinilos.databinding.FragmentAlbumBinding
import com.grupo15.vinilos.presentation.albums.create.AlbumCreateActivity
import com.grupo15.vinilos.presentation.albums.detail.AlbumDetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumFragment : Fragment(), OnAlbumClickListener {

    private var _binding: FragmentAlbumBinding? = null

    private val binding get() = _binding!!

    private val albumsViewModel: AlbumViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAlbumBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val albumRecycler: RecyclerView = binding.albumRecycle
        albumRecycler.adapter = AlbumAdapter(emptyList(), this)

        albumsViewModel.albums.observe(viewLifecycleOwner) { album ->
            val adapter = albumRecycler.adapter as AlbumAdapter
            adapter.updateAlbums(album)
        }

        albumsViewModel.error.observe(viewLifecycleOwner) { message ->
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
        albumsViewModel.getAlbums()
        binding.floatingActionButton.setOnClickListener { onCreateAlbum() }
        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAlbumClick(album: Album) {
        val intent = Intent(requireContext(), AlbumDetailActivity::class.java)
        intent.putExtra(AlbumDetailActivity.ALBUM_ID_KEY, album.id)
        startActivity(intent)
    }

    private fun onCreateAlbum() {
        val intent = Intent(requireContext(), AlbumCreateActivity::class.java)
        startActivity(intent)
    }

}
