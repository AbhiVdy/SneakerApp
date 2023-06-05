package com.example.sneakerapp.ui.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.sneakerapp.R
import com.example.sneakerapp.databinding.ActivityMainBinding
import com.example.sneakerapp.ui.views.fragment.CartFragment
import com.example.sneakerapp.ui.views.fragment.HomeFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navContoller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navContoller = navHostFragment.navController

        binding.bvMenu.setOnItemSelectedListener {
            when (it.title) {
                "Home" -> {
                    loadFragment(HomeFragment())
                    true
                }

                "Cart" -> {
                    loadFragment(CartFragment())
                    true
                }

                else -> {
                    false
                }
            }

        }

        loadFragment(HomeFragment())
    }

    override fun onSupportNavigateUp(): Boolean {
        return navContoller.navigateUp() || super.onSupportNavigateUp()
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainerView.id, fragment).commit()
    }
}