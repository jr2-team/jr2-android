package ru.jjba.jr2.presentation.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*
import ru.jjba.jr2.R

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        navController = findNavController(R.id.navController)
        NavigationUI.setupWithNavController(bottomNavigation, navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.kanaListFragment -> showUpButton()
                R.id.kanaDetailFragment -> showUpButton()
                R.id.kanjiGroupFragment -> showUpButton()
                R.id.kanjiListFragment -> showUpButton()
                R.id.kanjiDetailFragment -> showUpButton()
                R.id.wordGroupListFragment -> showUpButton()
                R.id.wordListFragment -> showUpButton()
                R.id.wordDetailFragment -> showUpButton()
                else -> showUpButton(false)
            }
        }
    }

    private fun showUpButton(isShown: Boolean = true) {
        supportActionBar?.setDisplayHomeAsUpEnabled(isShown)
    }

    override fun onSupportNavigateUp() = navController.navigateUp()
}
