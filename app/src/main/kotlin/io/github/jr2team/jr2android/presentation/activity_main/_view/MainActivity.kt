package io.github.jr2team.jr2android.presentation.activity_main._view

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import io.github.jr2team.jr2android.R
import io.github.jr2team.jr2android.common.dependency_injection.ViewModelInjectorUtil
import io.github.jr2team.jr2android.presentation._base._view.BaseActivity
import io.github.jr2team.jr2android.presentation.activity_main._viewmodel.MainViewModel
import io.github.jr2team.jr2android.presentation.nav_detail._viewmodel.NavDetailViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>() {
    override val layoutRes: Int = R.layout.activity_main

    private val navController by lazy { findNavController(R.id.navController) }
    private val navDetailViewModel by lazy {
        ViewModelProviders.of(this).get(NavDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(
            this,
            ViewModelInjectorUtil.provideMainViewModel()
        )[MainViewModel::class.java]
        setSupportActionBar(tbMain)
        setupNavigation()
    }

    override fun onSupportNavigateUp() = navController.navigateUp()

    override fun onBackPressed() {
        super.onBackPressed()
        when (navController.currentDestination?.id) {
            R.id.kanjiDetailFragment,
            R.id.wordDetailFragment -> Unit
            else -> navDetailViewModel.onNavigatedOutOfDetail()
        }
    }

    private fun setupNavigation() {
        NavigationUI.setupWithNavController(bottomNavigation, navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.kanaListFragment,
                R.id.kanaDetailFragment,
                R.id.kanjiGroupFragment,
                R.id.kanjiListFragment,
                R.id.kanjiDetailFragment,
                R.id.wordGroupListFragment,
                R.id.wordListFragment,
                R.id.wordDetailFragment -> showUpButton()
                else -> showUpButton(false)
            }
        }
    }

    private fun showUpButton(isShown: Boolean = true) =
        supportActionBar?.setDisplayHomeAsUpEnabled(isShown)
}
