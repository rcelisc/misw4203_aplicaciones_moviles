package com.grupo15.vinilos.presentation.performers.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.grupo15.vinilos.R
import com.grupo15.vinilos.data.model.Performer
import com.grupo15.vinilos.databinding.ActivityPerformerDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Locale

private const val BIRTHDATE_FORMAT: String = "dd/MM/yyyy"
private const val PROTOCOL_SCHEME: String = "https"

@AndroidEntryPoint
class PerformerDetailActivity : AppCompatActivity() {

    private val performerDetailViewModel: PerformerDetailViewModel by viewModels()

    private lateinit var binding: ActivityPerformerDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerformerDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.title_performer_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        performerDetailViewModel.performer.observe(this) { performer ->
            performer?.let { loadInfo(it) }
        }

        intent.getIntExtra(PERFORMER_ID_KEY, 0).let { id ->
            performerDetailViewModel.getPerformer(id)
        }

    }

    private fun loadInfo(performer: Performer) {
        binding.performerNameText.text = performer.name
        binding.performerDescriptionText.text = performer.description
        binding.performerBirthdayText.text =
            performer.birthDate?.let {
                SimpleDateFormat(BIRTHDATE_FORMAT, Locale.getDefault()).format(
                    it
                )
            }
        loadImage(performer)
    }

    private fun loadImage(performer: Performer) {
        Glide.with(this)
            .load(performer.image.toUri().buildUpon().scheme(PROTOCOL_SCHEME).build())
            .apply(
                RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(binding.performerImageImg)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val PERFORMER_ID_KEY: String = "PERFORMER_ID_KEY"
    }

}
