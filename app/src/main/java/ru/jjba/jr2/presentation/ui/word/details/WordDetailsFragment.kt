package ru.jjba.jr2.presentation.ui.word.details

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.TextView
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

    private val wordId: Long?
        get() = arguments?.getLong(WORD_ID)
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
        rvInterp.setHasFixedSize(true)
        rvInterp.layoutManager = LinearLayoutManager(context)
        rvInterp.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        rvInterp.adapter = interpAdapter

        ftvWord.setOnClickListener { speakOut((it as TextView).text.toString()) }
        tvBasicInterp.setOnClickListener { speakOut((it as TextView).text.toString()) }
    }

    override fun showWord(word: Word) {
        ftvWord.setFuriganaText("<ruby>${word.wordJp}<rt>${word.wordFurigana}</rt></ruby>")
        tvBasicInterp.text = word.basicInterp
        tvJlptLevel.text = "N${word.jlptLevel}"

        setTitle(word.wordJp)
    }

    override fun showInterps(interps: List<Interp>) {
        interpAdapter.interpList = interps
    }

    companion object {
        const val WORD_ID = "word id"

        fun newInstance(wordId: Long?): WordDetailsFragment =
                WordDetailsFragment().also {
                    it.arguments = bundleOf(WORD_ID to wordId)
                }
    }
}