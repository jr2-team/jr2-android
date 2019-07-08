package io.github.jr2team.jr2android.presentation.vocabulary._view

import androidx.navigation.fragment.findNavController
import io.github.jr2team.jr2android.R
import io.github.jr2team.jr2android.presentation._base._view.BaseFragment
import io.github.jr2team.jr2android.presentation.vocabulary._viewmodel.VocabViewModel
import kotlinx.android.synthetic.main.fragment_vocab.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class VocabFragment : BaseFragment<VocabViewModel>() {
    override var viewModel = VocabViewModel()
    override val layoutRes: Int = R.layout.fragment_vocab
    override val titleDefault: String
        get() = getString(R.string.vocab_fragment_title)

    override fun initContent() {
        val navController = findNavController()

        cvKana.onClick {
            navController.navigate(VocabFragmentDirections.actionVocabToKanaList())
        }

        cvKanji.onClick {
            navController.navigate(VocabFragmentDirections.actionVocabToKanjiGroup())
        }

        cvWord.onClick {
            navController.navigate(VocabFragmentDirections.actionVocabToWordGroup())
        }
    }
}