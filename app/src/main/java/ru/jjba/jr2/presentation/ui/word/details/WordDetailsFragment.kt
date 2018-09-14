package ru.jjba.jr2.presentation.ui.word.details

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_word_details.*
import org.jetbrains.anko.bundleOf
import org.zakariya.stickyheaders.StickyHeaderLayoutManager
import ru.jjba.jr2.R
import ru.jjba.jr2.domain.entity.Interp
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

    private val wordId: Long?
        get() = arguments?.getLong(WORD_ID)

    private lateinit var sections: MutableList<WordDetailsAdapter.Section>

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
        rvInterp.layoutManager = StickyHeaderLayoutManager()
        rvInterp.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        /**/
    }

    override fun showWord(word: Word) {

        val mainSection = WordDetailsAdapter.Section(
                WordDetailsAdapter.HEADER_TYPE_FUNCTIONAL,
                "FUNCTIONAL",
                mutableListOf(WordDetailsAdapter.ItemWord(word))
        )

        sections = mutableListOf(mainSection)

        setTitle(word.wordJp)
    }

    override fun showInterps(interps: List<Interp>) {
        //interpAdapter.interpList = interps
        val items = mutableListOf<WordDetailsAdapter.Item>().also { items ->
            interps.forEach {
                items.add(WordDetailsAdapter.ItemInterp(it))
            }
        }
        val section = WordDetailsAdapter.Section(
                WordDetailsAdapter.HEADER_TYPE_SECTION,
                "Interp 1 (${items.size})",
                items
        )
        sections.add(section)
        sections.add(section)
        sections.add(section)
        sections.add(section)
        sections.add(section)
        sections.add(section)
        sections.add(section)
        sections.add(section)
        sections.add(section)

        rvInterp.adapter = WordDetailsAdapter(sections)
    }

    companion object {
        const val WORD_ID = "word id"

        fun newInstance(wordId: Long?): WordDetailsFragment =
                WordDetailsFragment().also {
                    it.arguments = bundleOf(WORD_ID to wordId)
                }
    }
}