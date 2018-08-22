package ru.jjba.jr2.presentation.ui.main

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_main.*
import ru.jjba.jr2.R
import ru.jjba.jr2.domain.entity.Word
import ru.jjba.jr2.presentation.presenters.main.MainActivityPresenter
import ru.jjba.jr2.presentation.presenters.main.MainActivityView

class MainActivity : AppCompatActivity(), MainActivityView {
    private val wordAdapter = WordAdapter()

    @InjectPresenter
    lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)
        initContent()
    }

    fun initContent(){
        presenter.setContent()
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

    override fun showMessage(mes: String) {
        showMessage(mes)
    }
}
