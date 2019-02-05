package ru.jjba.jr2.presentation.ui.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseActivity
import ru.jjba.jr2.presentation.viewmodel.main.MainViewModel
import ru.jjba.jr2.presentation.viewmodel.shared.NavDetailViewModel
import ru.jjba.jr2.presentation.viewmodel.util.InjectorUtil

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
                InjectorUtil.provideMainViewModel()
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
