package ru.jjba.jr2.presentation.ui.main.activity

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_main.*
import ru.jjba.jr2.R
import ru.jjba.jr2.domain.entity.Word
import ru.jjba.jr2.presentation.presenters.main.activity.MainActivityPresenter
import ru.jjba.jr2.presentation.presenters.main.activity.MainActivityView
import ru.jjba.jr2.presentation.ui.word.list.WordAdapter

class MainActivity : MvpAppCompatActivity(), MainActivityView {
    private var wordAdapter = WordAdapter()

    @InjectPresenter
    lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        rvWord.layoutManager = LinearLayoutManager(this)
        rvWord.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        rvWord.adapter = wordAdapter
    }

    override fun setWordAdapter(words: List<Word>) {
        wordAdapter.wordList = words
    }
}
