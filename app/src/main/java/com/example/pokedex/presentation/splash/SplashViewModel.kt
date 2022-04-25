package com.example.pokedex.presentation.splash

import androidx.lifecycle.ViewModel
import com.example.pokedex.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel
@Inject constructor() : ViewModel() {

    val backgroundList = intArrayOf(
        R.drawable.charizard,
        R.drawable.charizard2,
        R.drawable.gengar,
        R.drawable.galarian,
        R.drawable.sylveon,
        R.drawable.umbreon,
        R.drawable.centiskorch,
        R.drawable.rillaboom
    )


}