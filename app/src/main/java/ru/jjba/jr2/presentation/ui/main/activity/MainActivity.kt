package ru.jjba.jr2.presentation.ui.main.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*
import ru.jjba.jr2.R
import ru.jjba.jr2.data.repository.word.WordDbRepository
import ru.jjba.jr2.domain.entity.Word
import ru.jjba.jr2.presentation.navigation.DefaultNavigator
import ru.jjba.jr2.presentation.navigation.NavigationHolder
import ru.jjba.jr2.presentation.navigation.NavigationHolder.router
import ru.jjba.jr2.presentation.navigation.Screen
import ru.jjba.jr2.presentation.presenters.main.activity.MainActivityPresenter
import ru.jjba.jr2.presentation.presenters.main.activity.MainActivityView
import ru.jjba.jr2.presentation.ui.kana.KanaFragment
import ru.jjba.jr2.presentation.ui.main.fragment.MainFragment
import ru.jjba.jr2.presentation.ui.tests.TestFragment
import ru.jjba.jr2.presentation.ui.word.details.WordDetailsFragment
import ru.jjba.jr2.presentation.ui.word.list.WordListFragment
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : MvpAppCompatActivity(), MainActivityView {

    @InjectPresenter
    lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initContent()
    }

    private fun initContent() {
        bottomNavigation.setOnSelectedItemChangeListener {
            when (it) {
                R.id.tiMain -> presenter.onMainClicked()
                R.id.tiKana -> presenter.onKanaClicked()
                R.id.tiWordList -> presenter.onWordListClicked()
                R.id.tiTest -> presenter.onTestClicked()
            }
        }

        // TODO: refactor to completable with rxjava or something
        // just for science
        val words = loadJSONFromAsset()
        val listType = object : TypeToken<List<Word>>() {}.type
        val wordList = Gson().fromJson<MutableList<Word>>(words, listType)
        wordList.removeAt(wordList.size - 1)
        WordDbRepository().insert(wordList).subscribeBy { }
    }

    private fun loadJSONFromAsset(): String? {
        var json: String? = null
        try {
            val inputStream = this.assets.open("words.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charset.forName("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
        }

        return json
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

    override fun onResumeFragments() {
        super.onResumeFragments()
        NavigationHolder.navigator.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        NavigationHolder.navigator.removeNavigator()
    }

    override fun onBackPressed() {
        router.exit()
    }
}
