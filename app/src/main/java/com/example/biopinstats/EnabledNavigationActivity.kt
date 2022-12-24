package com.example.biopinstats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.biopinstats.databinding.ActivityEnabledBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class EnabledNavigationActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityEnabledBinding = DataBindingUtil.setContentView(this, R.layout.activity_enabled)
        drawerLayout = binding.drawerLayout

        supportActionBar?.setDisplayShowTitleEnabled(false)

        val navView : NavigationView = findViewById(R.id.nav_view)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)

        navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.home -> navController.navigate(R.id.homeFragment)
                R.id.logs -> navController.navigate(R.id.logFragment)
                R.id.api -> navController.navigate(R.id.liveChangesFragment)
                R.id.about -> navController.navigate(R.id.faqFragment)
                R.id.faq -> navController.navigate(R.id.aboutFragment)
                R.id.logout -> finish()
            }
            true
        }
        bottomNavigationView = findViewById(R.id.bottom_nav_view)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> navController.navigate(R.id.homeFragment)
                R.id.logs -> navController.navigate(R.id.logFragment)
                R.id.api -> navController.navigate(R.id.liveChangesFragment)
            }
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }
}