package com.example.pokedex.presentation.onboarding

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokedex.R
import com.example.pokedex.databinding.ActivityOnboardingBinding
import com.example.pokedex.presentation.base.BaseActivity

class OnboardingActivity: BaseActivity<ActivityOnboardingBinding>(R.layout.activity_onboarding) {

    companion object {
        fun getStartIntent(context: Context) = Intent(context, OnboardingActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }
}