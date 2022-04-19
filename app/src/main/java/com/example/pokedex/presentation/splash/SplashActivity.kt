package com.example.pokedex.presentation.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import androidx.core.content.ContextCompat
import com.example.pokedex.R
import com.example.pokedex.databinding.ActivitySplashBinding
import com.example.pokedex.presentation.base.BaseActivity
import com.example.pokedex.presentation.onboarding.OnboardingActivity
import com.example.pokedex.util.SPLASH_DISPLAY_TIME
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    private val viewModel by viewModel<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getRandomBackgroung()
        Handler().postDelayed({
            startOnboarding()
        }, SPLASH_DISPLAY_TIME.toLong() )
    }

    private fun getRandomBackgroung() {
        binding.splashBackground.background =
            ContextCompat.getDrawable(this, viewModel.backgroundList.random())
    }

    private fun startOnboarding() {
        startActivity(OnboardingActivity.getStartIntent(this))
        finish()
    }
}