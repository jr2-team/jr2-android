package io.github.jr2team.jr2android.presentation.ui.vocab.word.detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_word_detail.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import io.github.jr2team.jr2android.R
import io.github.jr2team.jr2android.presentation.ui.NavDetailFragment
import io.github.jr2team.jr2android.presentation.viewmodel.shared.NavDetail
import io.github.jr2team.jr2android.presentation.viewmodel.util.observe
import io.github.jr2team.jr2android.presentation.viewmodel.vocab.word.WordDetailViewModel
import kotlin.random.Random

class WordDetailFragment : NavDetailFragment<WordDetailViewModel>() {
    override var viewModel = WordDetailViewModel()
    override val layoutRes = R.layout.fragment_word_detail
    override val titleDefault
        get() = ""

    private val args by navArgs<WordDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setArgs(args.wordId)
    }

    override fun initContent() {
        btnNavTo.onClick {
            val navToWordDetail = WordDetailFragmentDirections
                    .actionWordDetailFragmentSelf(Random.nextInt(1, 3000))
            findNavController().navigate(navToWordDetail)
        }
    }

    override fun observeData() = with(viewLifecycleOwner) {
        observe(viewModel.observeWord()) { word ->
            tvWordJp.text = word.value
            tvWordBasicInterpretation.text = word.basicInterpretation
            contentLadingDone(NavDetail(word.value, fragmentIdState, "(слово)"))
        }
    }
}