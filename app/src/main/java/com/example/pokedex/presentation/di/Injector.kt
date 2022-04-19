package com.example.pokedex.presentation.di

import com.example.pokedex.presentation.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {

    viewModel { SplashViewModel() }
}