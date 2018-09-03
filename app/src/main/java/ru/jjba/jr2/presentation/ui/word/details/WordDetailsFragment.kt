package ru.jjba.jr2.presentation.ui.word.details

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_word_details.*
import org.jetbrains.anko.bundleOf
import ru.jjba.jr2.R
import ru.jjba.jr2.domain.entity.Interp
import ru.jjba.jr2.domain.entity.Word
import ru.jjba.jr2.presentation.presenters.word.details.WordDetailsPresenter
import ru.jjba.jr2.presentation.presenters.word.details.WordDetailsView
import ru.jjba.jr2.presentation.ui.base.BaseFragment
import ru.jjba.jr2.presentation.ui.interp.InterpAdapter

class WordDetailsFragment : BaseFragment(), WordDetailsView {

    override val layoutRes: Int = R.layout.fragment_word_details
    override val titleDefault: String
        get() = getString(R.string.word_details_title)

    @InjectPresenter
    lateinit var presenter: WordDetailsPresenter

    private val wordId: String?
        get() = arguments?.getString(WORD_ID).takeUnless { it.isNullOrEmpty() }
    private val interpAdapter = InterpAdapter()

    @ProvidePresenter
    fun provideWordDetailsPresenter(): WordDetailsPresenter =
            WordDetailsPresenter(wordId)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isBnMainShown(false)

        initContent()
    }

    private fun initContent() {
        rvInterp.also {
            it.setHasFixedSize(true)
            it.layoutManager = LinearLayoutManager(context)
            it.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            it.adapter = interpAdapter
        }
    }

    override fun showWord(word: Word) {
        tvWordDetailsJp.text = word.wordJp
        tvWorddetailsFurigana.text = word.wordFurigana
        tvJlptLevel.text = "N${word.jlptLevel}"

        setTitle(word.wordJp)
    }

    override fun showInterps(interps: List<Interp>) {
        interpAdapter.interpList = interps
    }

    companion object {
        const val WORD_ID = "word id"

        fun newInstance(wordId: String?): WordDetailsFragment =
                WordDetailsFragment().also {
                    it.arguments = bundleOf(WORD_ID to wordId)
                }
    }
}