package ru.jjba.jr2.presentation.navigation

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder

object NavigationHolder {
    private val cicerone = Cicerone.create(DefaultRouter())

    val router: DefaultRouter = cicerone.router
    val navigator: NavigatorHolder = cicerone.navigatorHolder
}