package com.example.pokedex.presentation.home.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.pokedex.R
import com.example.pokedex.databinding.HomeFragmentBinding
import com.example.pokedex.presentation.base.BaseBindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.home_fragment.*

@AndroidEntryPoint
class HomeFragment : BaseBindingFragment<HomeFragmentBinding>(R.layout.home_fragment) {

    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadPokemonPaginated()
        observeViewModel()

    }

    private fun observeViewModel() {

        viewModel.isLoading.observe(viewLifecycleOwner) {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.responseApi.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), it.get(2).name, Toast.LENGTH_LONG).show()
        }


    }

}