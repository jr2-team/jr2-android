package io.github.jr2team.jr2android.presentation.activity_main._view

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import io.github.jr2team.jr2android.R
import io.github.jr2team.jr2android.common.dependency_injection.ViewModelInjectorUtil
import io.github.jr2team.jr2android.presentation._base._view.BaseActivity
import io.github.jr2team.jr2android.presentation.activity_main._viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>() {
    override val layoutRes: Int = R.layout.activity_main

    private val navController by lazy { findNavController(R.id.navController) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders
            .of(this, ViewModelInjectorUtil.provideMainViewModel())
            .get(MainViewModel::class.java)
        setSupportActionBar(tbMain)
        setupNavigation()
    }

    override fun onSupportNavigateUp() = navController.navigateUp()

    private fun setupNavigation() {
        NavigationUI.setupWithNavController(bottomNavigation, navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.kanjiListFragment -> showUpButton()
                else -> showUpButton(false)
            }
        }
    }

    private fun showUpButton(isShown: Boolean = true) =
        supportActionBar?.setDisplayHomeAsUpEnabled(isShown)
}
