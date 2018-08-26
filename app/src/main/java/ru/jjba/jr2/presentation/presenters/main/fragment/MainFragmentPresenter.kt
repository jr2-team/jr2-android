package ru.jjba.jr2.presentation.presenters.main.fragment

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.jjba.jr2.presentation.navigation.DefaultRouter
import ru.jjba.jr2.presentation.navigation.NavigationHolder
import ru.jjba.jr2.presentation.navigation.Screen

@InjectViewState
class MainFragmentPresenter(
        private val router: DefaultRouter = NavigationHolder.router
) : MvpPresenter<MainFragmentView>() {

}