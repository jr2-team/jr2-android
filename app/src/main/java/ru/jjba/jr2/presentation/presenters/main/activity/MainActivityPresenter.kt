package ru.jjba.jr2.presentation.presenters.main.activity

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.jjba.jr2.presentation.navigation.DefaultRouter
import ru.jjba.jr2.presentation.navigation.NavigationHolder
import ru.jjba.jr2.presentation.navigation.Screen

@InjectViewState
class MainActivityPresenter(
        private val router: DefaultRouter = NavigationHolder.router
) : MvpPresenter<MainActivityView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.newRootScreen(Screen.MAIN.title)
    }

    fun onMainClicked(){
        router.navigateTo(Screen.MAIN.title)
    }

    fun onWordListClicked(){
        router.navigateTo(Screen.WORD_LIST.title)
    }

    fun onWordDetailsClicked(){
        router.navigateTo(Screen.WORD_DETAILS.title)
    }

    fun onTestClicked(){
        router.navigateTo(Screen.TEST.title)
    }
}