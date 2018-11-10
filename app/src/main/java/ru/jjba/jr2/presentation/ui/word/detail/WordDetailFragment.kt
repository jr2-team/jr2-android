package ru.jjba.jr2.presentation.ui.word.detail

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_word_detail.*
import org.jetbrains.anko.bundleOf
import org.zakariya.stickyheaders.StickyHeaderLayoutManager
import ru.jjba.jr2.R
import ru.jjba.jr2.domain.entity.Interp
import ru.jjba.jr2.domain.entity.Word
import ru.jjba.jr2.presentation.presenters.word.detail.WordDetailPresenter
import ru.jjba.jr2.presentation.presenters.word.detail.WordDetailView
import ru.jjba.jr2.presentation.ui.base.BaseFragment

class WordDetailFragment : BaseFragment(), WordDetailView {
    override val layoutRes: Int = R.layout.fragment_word_detail
    override val titleDefault: String
        get() = getString(R.string.word_details_title)

    @InjectPresenter
    lateinit var presenter: WordDetailPresenter

    private val wordId: Long?
        get() = arguments?.getLong(WORD_ID)

    private lateinit var sections: MutableList<WordDetailAdapter.Section>

    @ProvidePresenter
    fun provideWordDetailsPresenter(): WordDetailPresenter =
            WordDetailPresenter(wordId)

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

        val mainSection = WordDetailAdapter.Section(
                WordDetailAdapter.HEADER_TYPE_FUNCTIONAL,
                "FUNCTIONAL",
                mutableListOf(WordDetailAdapter.ItemWord(word))
        )

        sections = mutableListOf(mainSection)

        setTitle(word.wordJp)
    }

    override fun showInterps(interps: List<Interp>) {
        //interpAdapter.interpList = interps
        val items = mutableListOf<WordDetailAdapter.Item>().also { items ->
            interps.forEach {
                items.add(WordDetailAdapter.ItemInterp(it))
            }
        }
        val section = WordDetailAdapter.Section(
                WordDetailAdapter.HEADER_TYPE_SECTION,
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

        rvInterp.adapter = WordDetailAdapter(sections)
    }

    companion object {
        const val WORD_ID = "word id"

        fun newInstance(wordId: Long?): WordDetailFragment =
                WordDetailFragment().also {
                    it.arguments = bundleOf(WORD_ID to wordId)
                }
    }
}