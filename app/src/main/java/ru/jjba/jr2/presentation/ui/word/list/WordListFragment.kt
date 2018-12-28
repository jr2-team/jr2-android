package ru.jjba.jr2.presentation.ui.word.list

import androidx.lifecycle.ViewModel
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseFragment
import ru.jjba.jr2.presentation.viewmodel.word.list.WordListViewModel

class WordListFragment : BaseFragment() {
    override var viewModel: ViewModel = WordListViewModel()
    override val layoutRes: Int = R.layout.fragment_word_list
    override val titleDefault: String
        get() = getString(R.string.word_list_title)

    private var wordAdapter = WordAdapter()

    override fun initContent() {
        /*rvWord.setHasFixedSize(true)
        rvWord.layoutManager = LinearLayoutManager(activity)
        rvWord.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
        rvWord.adapter = wordAdapter*/
    }

    override fun observeData() {
        //showWordList(wordList: List<Word>) wordAdapter.wordList = wordList
    }
}