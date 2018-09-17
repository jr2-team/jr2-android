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

    fun selectBottomMenuItem() {
        val screen = router.getCurrentScreenKey()
        if (screen != null) {
            viewState.selectBottomMenuItem(screen)
        }
    }

    fun onMainClicked(){
        router.navigateTo(Screen.MAIN.title)
    }

    fun onWordListClicked(){
        router.navigateTo(Screen.WORD_LIST.title)
    }

    fun onKanaClicked(){
        router.navigateTo(Screen.KANA.title)
    }

    fun onBackPressed(){
        val screen = router.getCurrentScreenKey()
        when (screen) {
            Screen.WORD_LIST.title, Screen.KANA.title -> router.newRootScreen(Screen.MAIN.title)
            else -> router.exit()
        }
    }

    fun setItemUpMode(mode: Boolean) = viewState.setItemUpMode(mode)
}