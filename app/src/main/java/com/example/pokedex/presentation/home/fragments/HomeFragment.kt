package com.example.pokedex.presentation.home.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.pokedex.R
import com.example.pokedex.databinding.HomeFragmentBinding
import com.example.pokedex.presentation.base.BaseBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseBindingFragment<HomeFragmentBinding>(R.layout.home_fragment) {

    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.responseApi.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), "${it.size}", Toast.LENGTH_SHORT).show()
        }
    }

}