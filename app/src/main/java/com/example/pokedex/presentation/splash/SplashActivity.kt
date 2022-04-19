package com.example.pokedex.presentation.splash

import android.annotation.SuppressLint
import android.os.Bundle
import com.example.pokedex.R
import com.example.pokedex.databinding.ActivitySplashBinding
import com.example.pokedex.presentation.base.BaseActivity
import org.koin.android.ext.android.inject

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    private val viewModel: SplashViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }
}