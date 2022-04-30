package com.example.pokedex.presentation.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.databinding.ActivityHomeBinding
import com.example.pokedex.presentation.base.BaseActivity
import com.example.pokedex.presentation.details.PokemonDetailsActivity
import com.example.pokedex.presentation.home.adapter.PokemonAdapter
import com.example.pokedex.util.QUANTITY
import com.example.pokedex.util.currency.PaginationListener
import com.example.pokedex.util.gone
import com.example.pokedex.util.showConfirmationDialog
import com.example.pokedex.util.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.home_empty_state.view.*

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
        initViews()

    }

    private fun initViews() {
        binding.apply {
            emptyState.try_again.setOnClickListener { viewModel.loadPokemonPaginated() }
        }
    }

    override fun onBackPressed() {
        showConfirmationDialog(getString(R.string.dialog_title),
            getString(R.string.dialog_message),
            true,
            { super.onBackPressed() })
    }

    private fun observeViewModel() {
        viewModel.isLoading.observe(this) {
            binding.loader.visibility = if (it) VISIBLE else GONE
        }

        viewModel.pokemonList.observe(this) { pokemonList ->
            pokeAdapter.data = pokemonList.toMutableList()
        }

        viewModel.error.observe(this) { error ->
            setupLayoutError(error)
        }
    }

    private fun setupRecyclerView() {
        binding.pokemonRecyclerView.apply {
            adapter = pokeAdapter
            pokeAdapter.onItemClickListener = {
                startActivity(PokemonDetailsActivity.getStartIntent(context, it))
            }

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                }
            })

            addOnScrollListener(object :
                PaginationListener(layoutManager as LinearLayoutManager, QUANTITY) {
                override fun loadMoreItems() {
                    viewModel.limit += QUANTITY
                    viewModel.loadPokemonPaginated()
                }

            })
        }
    }

    private fun setupLayoutError(error: Boolean) {
        binding.apply {
            if (error) {
                loader.gone()
                emptyState.visible()
                imageView.gone()
                pokemonRecyclerView.gone()
            } else {
                emptyState.gone()
                imageView.visible()
                pokemonRecyclerView.visible()
            }
        }
    }
}