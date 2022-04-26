package com.example.pokedex.presentation.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.pokedex.R
import com.example.pokedex.data.model.Results
import com.example.pokedex.databinding.ActivityPokemonDetailsBinding
import com.example.pokedex.presentation.base.BaseActivity
import com.example.pokedex.util.EXTRA_RESULTS

class PokemonDetailsActivity :
    BaseActivity<ActivityPokemonDetailsBinding>(R.layout.activity_pokemon_details) {

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

        Toast.makeText(this, resultsData!!.name, Toast.LENGTH_SHORT).show()

    }

    private fun receiveData() {
        resultsData = intent?.getSerializableExtra(EXTRA_RESULTS) as Results?
    }
}