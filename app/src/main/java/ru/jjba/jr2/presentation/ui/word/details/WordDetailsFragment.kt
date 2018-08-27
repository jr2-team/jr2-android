package ru.jjba.jr2.presentation.ui.word.details

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_word_details.*
import org.jetbrains.anko.bundleOf
import ru.jjba.jr2.R
import ru.jjba.jr2.domain.entity.Word
import ru.jjba.jr2.presentation.presenters.word.details.WordDetailsPresenter
import ru.jjba.jr2.presentation.presenters.word.details.WordDetailsView
import ru.jjba.jr2.presentation.ui.base.BaseFragment

class WordDetailsFragment : BaseFragment(), WordDetailsView {

    override val layoutRes: Int = R.layout.fragment_word_details
    override val titleDefault: String
        get() = getString(R.string.word_details_title)

    @InjectPresenter
    lateinit var presenter: WordDetailsPresenter

    private val wordId: String?
        get() = arguments?.getString(WORD_ID).takeUnless { it.isNullOrEmpty() }

    @ProvidePresenter
    fun provideWordDetailsPresenter(): WordDetailsPresenter =
            WordDetailsPresenter(wordId)

    override fun showWord(word: Word) {
        tvWordDetailsJp.text = word.wordJp
        tvWorddetailsFurigana.text = word.wordFurigana
    }

    companion object {
        const val WORD_ID = "word id"

        fun newInstance(wordId: String?): WordDetailsFragment =
                WordDetailsFragment().also {
                    it.arguments = bundleOf(WORD_ID to wordId)
                }
    }
}