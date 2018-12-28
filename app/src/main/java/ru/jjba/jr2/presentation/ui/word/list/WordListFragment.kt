package ru.jjba.jr2.presentation.ui.word.list

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_word_list.*
import org.jetbrains.anko.support.v4.act
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseFragment
import ru.jjba.jr2.presentation.viewmodel.word.list.WordListViewModel

class WordListFragment : BaseFragment<WordListViewModel>() {
    override var viewModel = WordListViewModel()
    override val layoutRes: Int = R.layout.fragment_word_list
    override val titleDefault: String
        get() = getString(R.string.word_list_title)

    private var wordAdapter = WordAdapter()

    override fun initContent() {
        rvWord.setHasFixedSize(true)
        rvWord.layoutManager = LinearLayoutManager(act)
        rvWord.addItemDecoration(DividerItemDecoration(act, LinearLayoutManager.VERTICAL))
        rvWord.adapter = wordAdapter
    }

    override fun observeData() {
        viewModel.getWords().observe(viewLifecycleOwner, Observer { words ->
            wordAdapter.words = words
        })
    }
}