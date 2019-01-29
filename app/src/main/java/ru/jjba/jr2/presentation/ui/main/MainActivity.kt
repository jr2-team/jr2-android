package ru.jjba.jr2.presentation.ui.main

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*
import ru.jjba.jr2.App
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseActivity
import ru.jjba.jr2.presentation.viewmodel.main.MainActivityViewModel

class MainActivity : BaseActivity<MainActivityViewModel>() {
    override var viewModel = MainActivityViewModel()
    override val layoutRes: Int = R.layout.activity_main

    private val navController by lazy {
        findNavController(R.id.navController)
    }
    private val detailNavigator = App.instance.detailNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        setupNavigation()
    }

    private fun setupNavigation() {
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

            /*when (destination.id) {
                R.id.wordDetailFragment ->
                    setCustomToolbarLayout(layoutRes = R.layout.toolbar_nav_detail)
                R.id.kanjiDetailFragment ->
                    setCustomToolbarLayout(layoutRes = R.layout.toolbar_nav_detail)
                else -> {
                    detailNavigator.navigatedOutOfDetail()
                    setCustomToolbarLayout(false)
                }
            }*/
        }
    }

    private fun showUpButton(isShown: Boolean = true) {
        supportActionBar?.setDisplayHomeAsUpEnabled(isShown)
    }

    private fun setCustomToolbarLayout(
            isShown: Boolean = true,
            @LayoutRes layoutRes: Int = -1
    ) {
        supportActionBar?.setDisplayShowCustomEnabled(isShown)
        if (layoutRes != -1 && isShown) {
            supportActionBar?.setCustomView(layoutRes)
        } else {
            supportActionBar?.customView = null
        }
    }

    override fun onSupportNavigateUp() = navController.navigateUp()

    override fun onBackPressed() {
        super.onBackPressed()
        when (navController.currentDestination?.id) {
            R.id.kanjiDetailFragment -> {
                detailNavigator.navigatedBack()
            }
            R.id.wordDetailFragment -> {
                detailNavigator.navigatedBack()
            }
        }
    }
}
