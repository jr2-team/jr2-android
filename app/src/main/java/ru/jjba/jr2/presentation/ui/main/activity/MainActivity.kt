package ru.jjba.jr2.presentation.ui.main.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import junit.framework.TestFailure
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.navigation.DefaultNavigator
import ru.jjba.jr2.presentation.navigation.NavigationHolder
import ru.jjba.jr2.presentation.navigation.Screen
import ru.jjba.jr2.presentation.presenters.main.activity.MainActivityPresenter
import ru.jjba.jr2.presentation.presenters.main.activity.MainActivityView
import ru.jjba.jr2.presentation.ui.main.fragment.MainFragment
import ru.jjba.jr2.presentation.ui.tests.TestFragment
import ru.jjba.jr2.presentation.ui.word.details.WordDetailsFragment
import ru.jjba.jr2.presentation.ui.word.list.WordListFragment

class MainActivity : MvpAppCompatActivity(), MainActivityView {

    @InjectPresenter
    lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private val navigator = object : DefaultNavigator(this, R.id.flContent) {
        override fun createFragment(screenKey: String?, data: Any?): Fragment? {
            return when (screenKey) {
                Screen.MAIN.title -> MainFragment()
                Screen.WORD_LIST.title -> WordListFragment()
                Screen.WORD_DETAILS.title -> WordDetailsFragment()
                Screen.TEST.title -> TestFragment()
                else -> null
            }
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        NavigationHolder.navigator.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        NavigationHolder.navigator.removeNavigator()
    }
}
