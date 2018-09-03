package ru.jjba.jr2.presentation.ui.word.list

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_word_list.*
import ru.jjba.jr2.R
import ru.jjba.jr2.domain.entity.Word
import ru.jjba.jr2.presentation.presenters.word.list.WordListPresenter
import ru.jjba.jr2.presentation.presenters.word.list.WordListView
import ru.jjba.jr2.presentation.ui.base.BaseFragment
import android.support.v7.app.AppCompatActivity



class WordListFragment : BaseFragment(), WordListView {

    override val layoutRes: Int = R.layout.fragment_word_list
    override val titleDefault: String
        get() = getString(R.string.word_list_title)

    private var wordAdapter = WordAdapter()

    @InjectPresenter
    lateinit var presenter: WordListPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initContent()
    }

    private fun initContent(){
        rvWord.setHasFixedSize(true)
        rvWord.layoutManager = LinearLayoutManager(activity)
        rvWord.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
        rvWord.adapter = wordAdapter
    }

    override fun showWordList(wordList: List<Word>) {
        wordAdapter.wordList = wordList
    }
}