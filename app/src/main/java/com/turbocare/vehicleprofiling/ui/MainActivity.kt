package com.turbocare.vehicleprofiling.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar

import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.turbocare.vehicleprofiling.R
import com.turbocare.vehicleprofiling.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()
    }

    private fun setupNavigation() {

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navGraph = navController.graph)

        binding.appBarLayout.collapsingToolbarLayout
            .setupWithNavController(binding.appBarLayout.toolbar, navController, appBarConfiguration)


        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.vehicleProfileFragment -> {
                    binding.appBarLayout.appBar.setExpanded(true)
                    binding.appBarLayout.registrationNumberSubtitle.visibility = View.VISIBLE
                }
                else -> {
                    binding.appBarLayout.appBar.setExpanded(false)
                    binding.appBarLayout.registrationNumberSubtitle.visibility = View.GONE
                }
            }
        }
    }
}