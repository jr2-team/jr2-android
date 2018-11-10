package ru.jjba.jr2.presentation.ui.kana.list

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.*
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_kana_list.*
import ru.jjba.jr2.R
import ru.jjba.jr2.domain.entity.Kana
import ru.jjba.jr2.presentation.presenters.kana.list.KanaPresenter
import ru.jjba.jr2.presentation.presenters.kana.list.KanaView
import ru.jjba.jr2.presentation.ui.base.BaseFragment
import ru.jjba.jr2.utils.ItemOffsetDecoration

class KanaFragment : BaseFragment(), KanaView {
    override val layoutRes: Int = R.layout.fragment_kana_list
    override val titleDefault: String
        get() = getString(R.string.kana_title)

    private var kanaAdapter = KanaAdapter()
    private var nigoriMode = false

    @InjectPresenter
    lateinit var presenter: KanaPresenter

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
            presenter.fillList(nigoriMode)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun setKanaList(list: List<Kana>) {
        if (list.isNotEmpty()) kanaAdapter.kanaList = list
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.fillList(nigoriMode)
        initContent()
    }

    private fun initContent() {
        rvKana.also {
            it.layoutManager = GridLayoutManager(activity, 5)
            it.setHasFixedSize(true)
            it.setItemViewCacheSize(30)
            it.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
            it.adapter = kanaAdapter
        }
        rvKana.addItemDecoration(ItemOffsetDecoration(4))
    }
}