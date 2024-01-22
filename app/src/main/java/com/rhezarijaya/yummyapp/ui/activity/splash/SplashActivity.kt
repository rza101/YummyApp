package com.rhezarijaya.yummyapp.ui.activity.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.rhezarijaya.core.util.Constants
import com.rhezarijaya.yummyapp.databinding.ActivitySplashBinding
import com.rhezarijaya.yummyapp.ui.activity.main.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            delay(Constants.SPLASH_SCREEN_DELAY)

            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }
}