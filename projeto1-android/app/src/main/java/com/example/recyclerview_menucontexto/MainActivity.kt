package com.example.recyclerview_menucontexto

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        val menuBottom = findViewById<BottomNavigationView>(R.id.bottomMenu)
        val navController = findNavController(R.id.my_nav_host_fragment)

        NavigationUI.setupWithNavController(menuBottom, navController)
        setSupportActionBar(toolbar)
        configureToolbar(title = "Home", enableBackButton = false)

        // Handle back press
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (!navController.navigateUp()) {
                    finish()
                }
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.my_nav_host_fragment).navigateUp()
    }

    private fun configureToolbar(title: String, enableBackButton: Boolean) {
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(enableBackButton)
    }
}
