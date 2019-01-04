package ru.jjba.jr2.presentation.ui.vocab.kana

import kotlinx.android.synthetic.main.app_bar_main.*
import org.jetbrains.anko.support.v4.act
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseFragment
import ru.jjba.jr2.presentation.viewmodel.vocab.kana.KanaListViewModel

class KanaListFragment : BaseFragment<KanaListViewModel>() {
    override var viewModel = KanaListViewModel()
    override val layoutRes: Int = R.layout.fragment_kana_list
    override val titleDefault: String
        get() = getString(R.string.kana_title)

    private var kanaAdapter = KanaListAdapter()
    private var nigoriMode = false

    override fun initContent() {
        // TODO: Set an action bar
        act.toolbar.setOnMenuItemClickListener { item ->
            when (item?.itemId) {
                R.id.miSwitchLang -> {
                    kanaAdapter.englishMode = !kanaAdapter.englishMode
                    true
                }
                R.id.miSwitchAlphabet -> {
                    kanaAdapter.katakanaMode = !kanaAdapter.katakanaMode
                    true
                }
                R.id.miSwitchNigori -> {
                    nigoriMode = !nigoriMode
                    //presenter.fillList(nigoriMode)
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
            //presenter.fillList(nigoriMode)
            /*rvKana.also {
                it.layoutManager = GridLayoutManager(activity, 5)
                it.setHasFixedSize(true)
                it.setItemViewCacheSize(30)
                it.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
                it.adapter = kanaAdapter
            }
            rvKana.addItemDecoration(ItemOffsetDecoration(4))*/
        }
    }

    override fun observeData() {
        /*override fun setKanaList(list: List<Kana>) {
        if (list.isNotEmpty()) kanaAdapter.kanaList = list
        }*/
    }
}