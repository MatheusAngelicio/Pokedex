package com.example.pokedex.presentation.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.pokedex.R
import com.example.pokedex.data.model.Results
import com.example.pokedex.databinding.ActivityPokemonDetailsBinding
import com.example.pokedex.presentation.base.BaseActivity
import com.example.pokedex.util.EXTRA_RESULTS
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailsActivity :
    BaseActivity<ActivityPokemonDetailsBinding>(R.layout.activity_pokemon_details) {

    private val viewModel: PokemonDetailsViewModel by viewModels()

    companion object {
        fun getStartIntent(context: Context, results: Results?): Intent =
            Intent(context, PokemonDetailsActivity::class.java).apply {
                putExtra(EXTRA_RESULTS, results)
            }
    }

    private var resultsData: Results? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        receiveData()
        setupPokemonInfo()


    }

    private fun receiveData() {
        resultsData = intent?.getSerializableExtra(EXTRA_RESULTS) as Results?
    }

    private fun setupPokemonInfo() {
        resultsData?.let {
            it.number?.let { id -> viewModel.getPokemonById(id) }
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.getPokemonProperty.observe(this) {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
        }
    }
}