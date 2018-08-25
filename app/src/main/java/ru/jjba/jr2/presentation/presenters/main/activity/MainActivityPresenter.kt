package ru.jjba.jr2.presentation.presenters.main.activity

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import ru.jjba.jr2.data.repository.interpretation.InterpretationDbRepository
import ru.jjba.jr2.data.repository.word.WordDbRepository
import ru.jjba.jr2.domain.entity.Interpretation
import ru.jjba.jr2.domain.entity.Word
import ru.jjba.jr2.presentation.navigation.DefaultRouter
import ru.jjba.jr2.presentation.navigation.NavigationHolder
import ru.jjba.jr2.presentation.navigation.Screen
import ru.jjba.jr2.presentation.presenters.main.fragment.MainFragmentView
import ru.jjba.jr2.presentation.ui.word.list.WordAdapter

@InjectViewState
class MainActivityPresenter(
        private val router: DefaultRouter = NavigationHolder.router
) : MvpPresenter<MainActivityView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.newRootScreen(Screen.MAIN.title)
    }

    fun onWordListClicked() {
        router.navigateTo(Screen.WORD_LIST.title)
    }

    fun onBackPressed() {
        router.exit()
    }

}