package ru.jjba.jr2.presentation.ui.main.fragment

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_main.*
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.navigation.DefaultRouter
import ru.jjba.jr2.presentation.navigation.NavigationHolder
import ru.jjba.jr2.presentation.navigation.Screen
import ru.jjba.jr2.presentation.presenters.main.fragment.MainFragmentPresenter
import ru.jjba.jr2.presentation.presenters.main.fragment.MainFragmentView
import ru.jjba.jr2.presentation.ui.base.BaseFragment

class MainFragment : BaseFragment(), MainFragmentView {

    override val layoutRes: Int = R.layout.fragment_main
    override val titleDefault: String
        get() = getString(R.string.main_title)

    @InjectPresenter
    lateinit var presenter: MainFragmentPresenter

    private val router: DefaultRouter = NavigationHolder.router

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}