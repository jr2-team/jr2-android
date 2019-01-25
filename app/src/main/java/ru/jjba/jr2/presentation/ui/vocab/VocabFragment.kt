package ru.jjba.jr2.presentation.ui.vocab

import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_vocab.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseFragment
import ru.jjba.jr2.presentation.viewmodel.vocab.VocabViewModel

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