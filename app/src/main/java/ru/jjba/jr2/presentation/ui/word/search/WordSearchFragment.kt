package ru.jjba.jr2.presentation.ui.word.search

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_word_search.*
import ru.jjba.jr2.R
import ru.jjba.jr2.data.repository.WordDbRepository
import ru.jjba.jr2.presentation.ui.base.BaseFragment
import ru.jjba.jr2.presentation.ui.word.list.WordAdapter

class WordSearchFragment : BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_word_search
    override val titleDefault: String = "Поиск"

    private var wordAdapter = WordAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initContent()
    }

    private fun initContent() {
        button.setOnClickListener {
            WordDbRepository().find("%${editText.text}%")
                    .first(emptyList())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                            onSuccess = {
                                wordAdapter.wordList = it
                            }
                    )
        }

        rvWord.setHasFixedSize(true)
        rvWord.layoutManager = LinearLayoutManager(activity)
        rvWord.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
        rvWord.adapter = wordAdapter
    }
}
