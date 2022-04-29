package com.example.pokedex.presentation.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.pokedex.R
import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.data.model.Results
import com.example.pokedex.data.model.Stats
import com.example.pokedex.data.model.Types
import com.example.pokedex.databinding.ActivityPokemonDetailsBinding
import com.example.pokedex.presentation.base.BaseActivity
import com.example.pokedex.presentation.details.adapter.TypePokemonAdapter
import com.example.pokedex.util.*
import com.example.pokedex.util.GradientUtil.Companion.getGradientColor
import com.skydoves.progressview.ProgressView
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
    private val gradientColors: MutableList<String> = mutableListOf()

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

        viewModel.error.observe(this) { error ->
            setupDialogError(error)
        }
        viewModel.isLoading.observe(this) {
            binding.loader.visibility = if (it) View.VISIBLE else View.GONE
        }

    }

    private fun setupPokemonData(pokemon: Pokemon) {
        binding.apply {
            resultsData?.let { result ->
                result.imageUrl = formattedNumber(result.number)
                pokemonName.text =
                    getString(R.string.pokemon_number, result.number.toString(), pokemon.name)
                weightPokemon.text =
                    getString(R.string.pokemon_weight, convertValue(pokemon.weight))
                heightPokemon.text =
                    getString(R.string.pokemon_height, convertValue(pokemon.height))
                SetupImageGlide.setImageUrl(
                    result.imageUrl, imagePokemon, imageProgress, this@PokemonDetailsActivity
                )
            }
            pokemon.types?.let {
                setupPokemonType(it)
                it.forEach { type ->
                    gradientColors.addAll(listOf(getGradientColor(type.type?.name)))
                    setupBackground(type.type?.name)
                }
            }
            pokemon.stats?.let { setupPokemonStats(it) }
        }
    }

    private fun setupPokemonStats(stats: List<Stats>) {
        binding.includeStatusProgress.apply {
            stats.getOrNull(HP)?.let { hp -> setupProgressStats(progressHp, hp) }
            stats.getOrNull(ATK)?.let { atk -> setupProgressStats(progressAtk, atk) }
            stats.getOrNull(DEF)?.let { def -> setupProgressStats(progressDef, def) }
            stats.getOrNull(SPA)?.let { spa -> setupProgressStats(progressSpa, spa) }
            stats.getOrNull(SPD)?.let { spd -> setupProgressStats(progressSpd, spd) }
            stats.getOrNull(SPE)?.let { spe -> setupProgressStats(progressSpe, spe) }
        }
    }

    private fun setupProgressStats(view: ProgressView, stats: Stats) {
        view.progress = stats.base_stat?.toFloat() ?: 0.0f
        view.labelText = getString(R.string.pokemon_status_progress, stats.base_stat)
    }

    private fun setupBackground(type: String?) {
        if (gradientColors.size >= 2) binding.layoutPokemon.background =
            (GradientUtil.setBackgroundGradient(gradientColors))
        else binding.layoutPokemon.setBackgroundResource(getTypeColor(type))
    }

    private fun setupDialogError(error: Boolean){
        if (error) {
            showErrorDetailsDaialog(getString(R.string.dialog_error_message),
                true,
                onConfirm = { finish() },
                { super.onBackPressed() })
            binding.blankLayout.visible()
        } else {
            binding.blankLayout.gone()
        }
    }

}