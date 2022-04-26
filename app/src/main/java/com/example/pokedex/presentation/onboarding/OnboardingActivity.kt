package com.example.pokedex.presentation.onboarding

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import com.example.pokedex.R
import com.example.pokedex.databinding.ActivityOnboardingBinding
import com.example.pokedex.presentation.base.BaseActivity
import com.example.pokedex.presentation.home.HomeActivity

class OnboardingActivity : BaseActivity<ActivityOnboardingBinding>(R.layout.activity_onboarding) {

    private var mp: MediaPlayer? = null
    private var currentSong = mutableListOf(R.raw.pokemonmusic)

    companion object {
        fun getStartIntent(context: Context) = Intent(context, OnboardingActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        controlSound(currentSong[0])
        initViews()
    }

    private fun initViews() {
        binding.startHomeButton.setOnClickListener {
            startActivity(HomeActivity.getStartIntent(this))
            finish()
        }
    }

    override fun onStop() {
        super.onStop()
        if (mp != null) mp?.pause()
    }

    override fun onResume() {
        super.onResume()
        mp?.start()
    }

    private fun controlSound(id: Int) {
        mp = MediaPlayer.create(this, id)
        mp?.start()
        mp?.isLooping = true
    }
}