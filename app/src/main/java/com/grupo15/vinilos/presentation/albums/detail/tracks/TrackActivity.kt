package com.grupo15.vinilos.presentation.albums.detail.tracks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import com.grupo15.vinilos.R
import com.grupo15.vinilos.databinding.ActivityTrackBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern

private const val INVALID_ALBUM_ID = -1

@AndroidEntryPoint
class TrackActivity : AppCompatActivity() {

    private val trackViewModel: TrackViewModel by viewModels()

    private var albumId: Int = INVALID_ALBUM_ID

    private lateinit var binding: ActivityTrackBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.title_track)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeActionContentDescription(getString(R.string.btn_go_back))

        intent.getIntExtra(ALBUM_ID_KEY, INVALID_ALBUM_ID).let { id ->
            albumId = id
        }

        trackViewModel.tracks.observe(this) {
            Toast.makeText(this, getString(R.string.add_track_message), Toast.LENGTH_LONG).show()
            finish()
        }

        trackViewModel.error.observe(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }

        binding.trackNameText.doOnTextChanged { text, _, _, _ ->
            binding.addTrackButton.isEnabled =
                !text.isNullOrEmpty() && binding.trackDurationText.text.isNotEmpty()
        }

        binding.trackDurationText.doOnTextChanged { text, _, _, _ ->
            binding.addTrackButton.isEnabled =
                !text.isNullOrEmpty() && binding.trackNameText.text.isNotEmpty()
        }

        binding.addTrackButton.setOnClickListener {
            if (validateDuration()) {
                trackViewModel.setTrackToAlbum(
                    albumId = this.albumId,
                    nameTrack = binding.trackNameText.text.toString(),
                    duration = binding.trackDurationText.text.toString()
                )
            } else {
                Toast.makeText(this, getString(R.string.invalid_format_duration), Toast.LENGTH_LONG)
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

    private fun validateDuration(): Boolean {
        val patron = Pattern.compile("^\\d{2}:\\d{2}:\\d{2}$")
        val matcher = patron.matcher(binding.trackDurationText.text)
        return matcher.matches()
    }

    companion object {
        const val ALBUM_ID_KEY: String = "ALBUM_ID_KEY"
    }

}