package ru.jjba.jr2.presentation.ui.kana

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_kana.*
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.presenters.kana.KanaPresenter
import ru.jjba.jr2.presentation.presenters.kana.KanaView
import ru.jjba.jr2.presentation.ui.base.BaseFragment
import ru.jjba.jr2.utils.ItemOffsetDecoration

class KanaFragment : BaseFragment(), KanaView {

    override val layoutRes: Int = R.layout.fragment_kana
    override val titleDefault: String
        get() = getString(R.string.kana_title)

    private var kanaAdapter = KanaAdapter()

    @InjectPresenter
    lateinit var presenter: KanaPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initContent()
    }

    private fun initContent() {
        kanaAdapter = presenter.getAdapter()
        setRecyclerViewParam()
    }

    override fun setRecyclerViewParam() {
        rvKana.also {

            it.layoutManager = GridLayoutManager(activity, 5)
            it.addItemDecoration(ItemOffsetDecoration(4))
            it.adapter = kanaAdapter
        }
    }
}