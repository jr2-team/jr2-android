package ru.jjba.jr2.presentation.ui.word.detail

import android.os.Bundle
import android.view.View
import org.jetbrains.anko.bundleOf
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseFragment

class WordDetailFragment : BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_word_detail
    override val titleDefault: String
        get() = getString(R.string.word_details_title)

    private val wordId: Long?
        get() = arguments?.getLong(WORD_ID)

    private lateinit var sections: MutableList<WordDetailAdapter.Section>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isBnMainShown(false)

        initContent()
    }

    private fun initContent() {
        /*rvInterp.setHasFixedSize(true)
        rvInterp.layoutManager = StickyHeaderLayoutManager()
        rvInterp.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))*/
    }

    /*override fun showWord(word: Word) {

        val mainSection = WordDetailAdapter.Section(
                WordDetailAdapter.HEADER_TYPE_FUNCTIONAL,
                "FUNCTIONAL",
                mutableListOf(WordDetailAdapter.ItemWord(word))
        )

        sections = mutableListOf(mainSection)

        setTitle(word.wordJp)
    }*/

    /*override fun showInterps(interps: List<Interp>) {
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
    }*/

    companion object {
        const val WORD_ID = "word id"

        fun newInstance(wordId: Long?): WordDetailFragment =
                WordDetailFragment().also {
                    it.arguments = bundleOf(WORD_ID to wordId)
                }
    }
}