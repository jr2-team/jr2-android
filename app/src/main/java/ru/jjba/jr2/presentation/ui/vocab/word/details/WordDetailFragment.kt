package ru.jjba.jr2.presentation.ui.vocab.word.details

import android.os.Bundle
import android.view.View
import org.jetbrains.anko.bundleOf
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseFragment
import ru.jjba.jr2.presentation.viewmodel.vocab.word.WordListViewModel

class WordDetailFragment : BaseFragment<WordListViewModel>() {
    override var viewModel = WordListViewModel()
    override val layoutRes: Int = R.layout.fragment_word_detail
    override val titleDefault: String = "Word Detail"

    private val wordId: Long?
        get() = arguments?.getLong(WORD_ID)

    private lateinit var sections: MutableList<WordDetailsAdapter.Section>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showBottomNavigation(false)

        initContent()
    }

    override fun initContent() {
        /*rvInterp.setHasFixedSize(true)
        rvInterp.layoutManager = StickyHeaderLayoutManager()
        rvInterp.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))*/
    }

    override fun observeData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /*override fun showWord(word: Word) {

        val mainSection = WordDetailsAdapter.Section(
                WordDetailsAdapter.HEADER_TYPE_FUNCTIONAL,
                "FUNCTIONAL",
                mutableListOf(WordDetailsAdapter.ItemWord(word))
        )

        sections = mutableListOf(mainSection)

        setTitle(word.wordJp)
    }*/

    /*override fun showInterps(interps: List<Interpretation>) {
        //interpAdapter.interpList = interps
        val items = mutableListOf<WordDetailsAdapter.Item>().also { items ->
            interps.forEach {
                items.add(WordDetailsAdapter.ItemInterp(it))
            }
        }
        val section = WordDetailsAdapter.Section(
                WordDetailsAdapter.HEADER_TYPE_SECTION,
                "Interpretation 1 (${items.size})",
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
    }*/

    companion object {
        const val WORD_ID = "word id"

        fun newInstance(wordId: Long?): WordDetailFragment =
                WordDetailFragment().also {
                    it.arguments = bundleOf(WORD_ID to wordId)
                }
    }
}