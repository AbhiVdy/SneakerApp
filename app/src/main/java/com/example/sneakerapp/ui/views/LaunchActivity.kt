package com.example.sneakerapp.ui.views

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.sneakerapp.Constants.SPLASH_DELAY
import com.example.sneakerapp.databinding.ActivityLaunchBinding
import com.example.sneakerapp.ui.view_model.SneakerViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class LaunchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLaunchBinding
    private val sneakerViewModel: SneakerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLaunchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Clearing Data if existed
        sneakerViewModel.clearAllData()

        //Navigating to main activity
        GlobalScope.launch {
            delay(SPLASH_DELAY)
            navigateToActivity()
        }
    }

    private fun navigateToActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}