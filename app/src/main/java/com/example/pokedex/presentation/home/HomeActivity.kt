package com.example.pokedex.presentation.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.pokedex.R
import com.example.pokedex.data.model.Results
import com.example.pokedex.databinding.ActivityHomeBinding
import com.example.pokedex.presentation.base.BaseActivity
import com.example.pokedex.presentation.details.PokemonDetailsActivity
import com.example.pokedex.presentation.home.adapter.PokemonAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {

    private val viewModel: HomeViewModel by viewModels()

    var pokeAdapter = PokemonAdapter()

    companion object {
        fun getStartIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.isLoading.observe(this) {
            //binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
            binding.loader.visibility = if (it) View.VISIBLE else View.GONE

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