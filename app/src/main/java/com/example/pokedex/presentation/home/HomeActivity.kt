package com.example.pokedex.presentation.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.pokedex.R
import com.example.pokedex.data.model.Results
import com.example.pokedex.databinding.ActivityMainBinding
import com.example.pokedex.presentation.base.BaseActivity
import com.example.pokedex.presentation.details.PokemonDetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel: HomeViewModel by viewModels()

    var pokeAdapter = PokemonAdapter()

    companion object {
        fun getStartIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.loadPokemonPaginated()
        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.isLoading.observe(this) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.responseApi.observe(this) { pokemonList ->
            setupRecyclerView(pokemonList)
        }

    }

    private fun setupRecyclerView(pokemonList: List<Results>) {
        binding.pokemonRecyclerView.apply {
            adapter = pokeAdapter
            pokeAdapter.data = pokemonList.toMutableList()

            pokeAdapter.onItemClickListener = {
                startActivity(PokemonDetailsActivity.getStartIntent(context, it))
            }
        }

    }
}