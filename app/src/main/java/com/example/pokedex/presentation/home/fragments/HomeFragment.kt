package com.example.pokedex.presentation.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pokedex.R
import com.example.pokedex.databinding.HomeFragmentBinding
import com.example.pokedex.presentation.base.BaseBindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseBindingFragment<HomeFragmentBinding>(R.layout.home_fragment) {

    private val viewModel by viewModel<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}