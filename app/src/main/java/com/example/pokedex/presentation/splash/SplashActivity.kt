package com.example.pokedex.presentation.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.pokedex.R
import com.example.pokedex.databinding.ActivitySplashBinding
import com.example.pokedex.presentation.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    private val viewModel by viewModel<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getRandomBackgroung()
    }

    private fun getRandomBackgroung() {
        binding.splashBackground.background =
            ContextCompat.getDrawable(this, viewModel.backgroundList.random())
    }
}