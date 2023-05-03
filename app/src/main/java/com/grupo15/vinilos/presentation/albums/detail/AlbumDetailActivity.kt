package com.grupo15.vinilos.presentation.albums.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import com.grupo15.vinilos.R
import com.grupo15.vinilos.data.model.Album
import com.grupo15.vinilos.databinding.ActivityAlbumDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Locale

private const val RELEASE_DATE_FORMAT: String = "dd/MM/yyyy"
private const val TRACKS_SEPARATOR: String = ", "

@AndroidEntryPoint
class AlbumDetailActivity : AppCompatActivity() {

    private val albumDetailViewModel: AlbumDetailViewModel by viewModels()

    private lateinit var binding: ActivityAlbumDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.title_album_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        albumDetailViewModel.album.observe(this) { album ->
            album?.let { loadInfo(it) }
        }

        intent.getStringExtra(ALBUM_ID_KEY)?.let { id ->
            albumDetailViewModel.getAlbum(id)
        }

    }

    private fun loadInfo(album: Album) {
        binding.albumNameText.text = album.name
        binding.albumCoverText.text = album.cover
        binding.albumReleaseDateText.text = album.releaseDate.let {
            SimpleDateFormat(RELEASE_DATE_FORMAT, Locale.getDefault()).format(
                it
            )
        }
        binding.albumGenreText.text = album.genre
        binding.albumRecordLabelText.text = album.recordLabel
        binding.albumDescriptionText.text = album.description
        binding.albumTracksText.text =
            album.tracks.joinToString(TRACKS_SEPARATOR) { it.name }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val ALBUM_ID_KEY: String = "ALBUM_ID_KEY"
    }

}
