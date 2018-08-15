package ru.jjba.jr2.presentation.ui.main

import android.support.design.widget.NavigationView
import android.view.MenuItem
import com.arellomobile.mvp.presenter.InjectPresenter
import ru.jjba.jr2.presentation.presenters.main.MainActivityPresenter
import ru.jjba.jr2.presentation.presenters.main.MainActivityView
import ru.jjba.jr2.presentation.ui.base.BaseActivity
import ru.terrakok.cicerone.Navigator

class MainActivity : BaseActivity(),NavigationView.OnNavigationItemSelectedListener, MainActivityView  {

    @InjectPresenter
    val presenter: MainActivityPresenter = MainActivityPresenter()

    override val navigator: Navigator
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun updateNavHeader(profileName: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
