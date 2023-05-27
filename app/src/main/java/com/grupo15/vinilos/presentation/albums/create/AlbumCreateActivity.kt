package com.grupo15.vinilos.presentation.albums.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import com.grupo15.vinilos.R
import com.grupo15.vinilos.databinding.ActivityAlbumCreateBinding
import com.grupo15.vinilos.presentation.albums.AlbumViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern

@AndroidEntryPoint
class AlbumCreateActivity : AppCompatActivity() {

    private val albumViewModel: AlbumViewModel by viewModels()
    private lateinit var binding: ActivityAlbumCreateBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityAlbumCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.add_album_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        albumViewModel.albumCreated.observe(this) { album ->
            if (album !== null) {
                Toast.makeText(this, getString(R.string.create_album_message), Toast.LENGTH_LONG)
                    .show()
                finish()
            }
        }

        albumViewModel.error.observe(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }

        binding.addAlbumButton.setOnClickListener {
            if (validateReleaseDate()) {
                albumViewModel.createAlbum(
                    name = binding.albumNameText.text.toString(),
                    cover = binding.albumCoverText.text.toString(),
                    releaseDate = binding.albumReleaseDateText.text.toString(),
                    description = binding.albumDescriptionText.text.toString(),
                    genre = binding.albumGenreText.selectedItem.toString(),
                    recordLabel = binding.albumRecordLabelText.selectedItem.toString()
                )
            } else {
                Toast.makeText(this, getString(R.string.invalid_format_date), Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun validateReleaseDate(): Boolean {
        val patron = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$")
        val matcher = patron.matcher(binding.albumReleaseDateText.text)
        return matcher.matches()
    }
}