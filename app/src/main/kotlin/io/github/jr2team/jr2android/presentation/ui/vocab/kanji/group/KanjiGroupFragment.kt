package io.github.jr2team.jr2android.presentation.ui.vocab.kanji.group

import android.os.Bundle
import android.view.View
import io.github.jr2team.jr2android.R
import io.github.jr2team.jr2android.presentation.ui.BaseFragment
import io.github.jr2team.jr2android.presentation.viewmodel.vocab.kanji.KanjiGroupViewModel

class KanjiGroupFragment : BaseFragment<KanjiGroupViewModel>() {
    override var viewModel = KanjiGroupViewModel()
    override val layoutRes = R.layout.fragment_kanji_group
    override val titleDefault: String
        get() = getString(R.string.kanji_group_fragment_title)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBottomNavigation(false)
    }
}