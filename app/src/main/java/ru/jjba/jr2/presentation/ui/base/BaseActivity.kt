package ru.jjba.jr2.presentation.ui.base

import com.arellomobile.mvp.MvpAppCompatActivity
import ru.jjba.jr2.presentation.navigation.NavigationHolder
import ru.terrakok.cicerone.Navigator

abstract class BaseActivity : MvpAppCompatActivity() {
    protected abstract val navigator: Navigator

    override fun onResumeFragments() {
        super.onResumeFragments()
        NavigationHolder.navigator.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        NavigationHolder.navigator.removeNavigator()
    }
}