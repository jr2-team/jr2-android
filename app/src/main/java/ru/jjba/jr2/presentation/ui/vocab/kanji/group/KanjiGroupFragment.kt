package ru.jjba.jr2.presentation.ui.vocab.kanji.group

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseFragment
import ru.jjba.jr2.presentation.viewmodel.vocab.kanji.KanjiGroupViewModel

class KanjiGroupFragment : BaseFragment<KanjiGroupViewModel>() {
    override var viewModel = KanjiGroupViewModel()
    override val layoutRes = R.layout.fragment_kanji_group
    override val titleDefault: String
        get() = getString(R.string.kanji_group_fragment_title)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBottomNavigation(false)
    }

    override fun initContent() {
        viewModel.test()
    }

    override fun observeData() = with(viewModel) {
        kanjicomp.observe(viewLifecycleOwner, Observer {

        })
        kana.observe(viewLifecycleOwner, Observer {

        })
    }
}