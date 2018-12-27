package ru.jjba.jr2.presentation.ui.word.list

import android.os.Bundle
import android.view.View
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseFragment

class WordListFragment : BaseFragment() {

    override val layoutRes: Int = R.layout.fragment_word_list
    override val titleDefault: String
        get() = getString(R.string.word_list_title)

    private var wordAdapter = WordAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initContent()
    }

    private fun initContent() {
       /* rvWord.setHasFixedSize(true)
        rvWord.layoutManager = LinearLayoutManager(activity)
        rvWord.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
        rvWord.adapter = wordAdapter*/
    }

    /*override fun showWordList(wordList: List<Word>) {
        wordAdapter.wordList = wordList
    }*/
}