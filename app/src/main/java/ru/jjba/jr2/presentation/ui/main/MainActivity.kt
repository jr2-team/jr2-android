package ru.jjba.jr2.presentation.ui.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*
import ru.jjba.jr2.App
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseActivity
import ru.jjba.jr2.presentation.viewmodel.main.MainViewModel
import ru.jjba.jr2.presentation.viewmodel.util.InjectorUtil

class MainActivity : BaseActivity<MainViewModel>() {
    override val layoutRes: Int = R.layout.activity_main

    private val navController by lazy { findNavController(R.id.navController) }
    //private val detailNavigator = App.instance.detailNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO: Переписать дженрик фабрику для viewmodel
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
        /*when (navController.currentDestination?.id) {
            R.id.kanjiDetailFragment -> detailNavigator.navigatedBack()
            R.id.wordDetailFragment -> detailNavigator.navigatedBack()
            else -> detailNavigator.navigatedOutOfDetail()
        }*/
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

           /* when (destination.id) {
                R.id.wordDetailFragment -> {
                }
                R.id.kanjiDetailFragment -> {
                }
                else -> detailNavigator.navigatedOutOfDetail()
            }*/
        }
    }

    private fun showUpButton(isShown: Boolean = true) =
            supportActionBar?.setDisplayHomeAsUpEnabled(isShown)
}
