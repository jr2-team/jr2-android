package ru.jjba.jr2.presentation.ui.main.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.navigation.DefaultNavigator
import ru.jjba.jr2.presentation.navigation.NavigationHolder
import ru.jjba.jr2.presentation.navigation.Screen
import ru.jjba.jr2.presentation.presenters.main.activity.MainActivityPresenter
import ru.jjba.jr2.presentation.presenters.main.activity.MainActivityView
import ru.jjba.jr2.presentation.ui.kana.KanaFragment
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

        setSupportActionBar(toolbar)

        initContent()
    }

    private fun initContent() {
        navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.tiMain -> presenter.onMainClicked().let { true }
                R.id.tiKana -> presenter.onKanaClicked().let { true }
                R.id.tiWordList -> presenter.onWordListClicked().let { true }
                else -> presenter.onMainClicked().let { true }
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

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    override fun selectBottomMenuItem(screenKey: String) {
        when (screenKey) {
            Screen.MAIN.title -> navigation.selectedItemId = R.id.tiMain
            Screen.WORD_LIST.title -> navigation.selectedItemId = R.id.tiWordList
            Screen.KANA.title -> navigation.selectedItemId = R.id.tiKana
        }
    }

    private val navigator = object : DefaultNavigator(this, R.id.flContent) {
        override fun createFragment(screenKey: String?, data: Any?): Fragment? {
            return when (screenKey) {
                Screen.MAIN.title -> MainFragment()
                Screen.WORD_LIST.title -> WordListFragment()
                Screen.WORD_DETAILS.title -> WordDetailsFragment.newInstance(data as? String)
                Screen.TEST.title -> TestFragment()
                Screen.KANA.title -> KanaFragment()
                else -> null
            }
        }
    }


}
