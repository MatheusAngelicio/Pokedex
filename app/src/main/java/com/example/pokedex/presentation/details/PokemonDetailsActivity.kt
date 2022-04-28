package com.example.pokedex.presentation.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.pokedex.R
import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.data.model.Results
import com.example.pokedex.data.model.Types
import com.example.pokedex.databinding.ActivityPokemonDetailsBinding
import com.example.pokedex.presentation.base.BaseActivity
import com.example.pokedex.presentation.details.adapter.TypePokemonAdapter
import com.example.pokedex.util.EXTRA_RESULTS
import com.example.pokedex.util.SetupImageGlide
import com.example.pokedex.util.convertValue
import com.example.pokedex.util.formattedNumber
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
    private val typeList: TypePokemonAdapter by lazy { TypePokemonAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        receiveData()
        setupPokemonId()


    }

    private fun receiveData() {
        resultsData = intent?.getSerializableExtra(EXTRA_RESULTS) as Results?
    }

    private fun setupPokemonId() {
        resultsData?.let {
            it.number?.let { number -> viewModel.getPokemonById(number) }
        }

        observeViewModel()
    }

    private fun setupPokemonType(types: List<Types>) {
        typeList.data = types.toMutableList()
        binding.recyclerViewType.adapter = typeList
    }

    private fun observeViewModel() {
        viewModel.getPokemonProperty.observe(this) {
            setupPokemonData(it)
        }

        viewModel.error.observe(this) {
            if (it) Toast.makeText(this, "Algo deu errado", Toast.LENGTH_LONG).show()
        }
    }

    private fun setupPokemonData(pokemon: Pokemon) {
        binding.apply {
            resultsData?.let { result ->
                result.imageUrl = formattedNumber(result.number)
                pokemonName.text = pokemon.name
                weightPokemon.text =
                    getString(R.string.pokemon_weight, convertValue(pokemon.weight))
                heightPokemon.text =
                    getString(R.string.pokemon_height, convertValue(pokemon.height))
                SetupImageGlide.setImageUrl(
                    result.imageUrl, imagePokemon, imageProgress, this@PokemonDetailsActivity
                )
            }
            pokemon.types?.let { setupPokemonType(it) }
        }
    }


}