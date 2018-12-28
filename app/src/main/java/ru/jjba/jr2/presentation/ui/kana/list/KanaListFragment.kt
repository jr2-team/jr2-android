package ru.jjba.jr2.presentation.ui.kana.list

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.ViewModel
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseFragment
import ru.jjba.jr2.presentation.viewmodel.kana.list.KanaListViewModel

class KanaListFragment : BaseFragment() {
    override var viewModel: ViewModel = KanaListViewModel()
    override val layoutRes: Int = R.layout.fragment_kana_list
    override val titleDefault: String
        get() = getString(R.string.kana_title)

    private var kanaAdapter = KanaAdapter()
    private var nigoriMode = false

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.kana_toolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
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


    override fun initContent() {
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

    override fun observeData() {
        /*override fun setKanaList(list: List<Kana>) {
        if (list.isNotEmpty()) kanaAdapter.kanaList = list
        }*/
    }
}