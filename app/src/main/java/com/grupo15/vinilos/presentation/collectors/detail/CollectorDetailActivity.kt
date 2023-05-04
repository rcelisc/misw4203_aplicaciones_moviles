package com.grupo15.vinilos.presentation.collectors.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.grupo15.vinilos.R
import com.grupo15.vinilos.databinding.ActivityCollectorDetailBinding
import dagger.hilt.android.AndroidEntryPoint

private const val FAVORITES_SEPARATOR: String = ", "

@AndroidEntryPoint
class CollectorDetailActivity : AppCompatActivity() {

    private val collectorDetailViewModel: CollectorDetailViewModel by viewModels()

    private lateinit var binding: ActivityCollectorDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCollectorDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.title_collector_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        collectorDetailViewModel.collector.observe(this) { collector ->
            binding.collectorNameText.text = collector?.name
            binding.collectorPhoneText.text = collector?.telephone
            binding.collectorEmailText.text = collector?.email
            binding.collectorFavoritesText.text =
                collector?.favoritePerformers?.joinToString(FAVORITES_SEPARATOR) { it.name }
        }

        intent.getStringExtra(COLLECTOR_ID_KEY)?.let { id ->
            collectorDetailViewModel.getCollector(id)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val COLLECTOR_ID_KEY: String = "COLLECTOR_ID_KEY"
    }

}
