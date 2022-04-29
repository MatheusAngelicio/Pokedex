package com.example.pokedex.presentation.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.R
import com.example.pokedex.data.model.Results
import com.example.pokedex.databinding.ActivityHomeBinding
import com.example.pokedex.presentation.base.BaseActivity
import com.example.pokedex.presentation.details.PokemonDetailsActivity
import com.example.pokedex.presentation.home.adapter.PokemonAdapter
import com.example.pokedex.util.QUANTITY
import com.example.pokedex.util.currency.PaginationListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.view.*

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
        setupRecyclerView()

    }

    private fun observeViewModel() {
        viewModel.isLoading.observe(this) {
            binding.loader.visibility = if (it) View.VISIBLE else View.GONE

        }

        viewModel.responseApi.observe(this) { pokemonList ->
            pokeAdapter.data = pokemonList.toMutableList()

        }

    }

    private fun setupRecyclerView() {
        binding.pokemonRecyclerView.apply {
            adapter = pokeAdapter

            pokeAdapter.onItemClickListener = {
                startActivity(PokemonDetailsActivity.getStartIntent(context, it))
            }

            addOnScrollListener(object : PaginationListener(layoutManager as LinearLayoutManager, QUANTITY){
                override fun loadMoreItems() {
                    viewModel.limit += QUANTITY
                    viewModel.loadPokemonPaginated()
                }

                override val isLoading: Boolean get() = false

            })
        }

    }
}