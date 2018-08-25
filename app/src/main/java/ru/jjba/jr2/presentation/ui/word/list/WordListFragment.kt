package ru.jjba.jr2.presentation.ui.word.list

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_word_list.*
import ru.jjba.jr2.R
import ru.jjba.jr2.domain.entity.Word
import ru.jjba.jr2.presentation.presenters.word.list.WordListPresenter
import ru.jjba.jr2.presentation.presenters.word.list.WordListView
import ru.jjba.jr2.presentation.ui.base.BaseFragment

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
        setRecyclerViewParam()
    }

    private fun initContent(){
        presenter.setContent()
        wordAdapter = presenter.getAdapter()
        setRecyclerViewParam()
    }

    override fun setRecyclerViewParam() {
        rvWord.setHasFixedSize(true)
        rvWord.layoutManager = LinearLayoutManager(activity)
        rvWord.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
        rvWord.adapter = wordAdapter
    }

    override fun setWordAdapter(words: List<Word>) {
        wordAdapter.wordList = words
    }
}