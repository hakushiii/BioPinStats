package com.example.biopinstats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.biopinstats.databinding.ActivityEnabledBinding

class EnabledNavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityEnabledBinding = DataBindingUtil.setContentView(this, R.layout.activity_enabled)
        val navController = this.findNavController(R.id.myNavHostFragment)
    }
}