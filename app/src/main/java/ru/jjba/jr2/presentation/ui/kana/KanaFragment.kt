package ru.jjba.jr2.presentation.ui.kana

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.view.*
import android.widget.Switch
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

    @InjectPresenter
    lateinit var presenter: KanaPresenter

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.kana_toolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        R.id.miSettings -> createSettingsDialog().let { true }
        else -> super.onOptionsItemSelected(item)
    }

    private fun createSettingsDialog() {
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_kana_settings, null)
        val swEnglishMode = view.findViewById<Switch>(R.id.swEnglishMode)
        val swKatakanaMode = view.findViewById<Switch>(R.id.swKatakanaMode)

        swEnglishMode.isChecked = presenter.englishMode
        swKatakanaMode.isChecked = presenter.katakanaMode

        swEnglishMode.setOnCheckedChangeListener {
            _, it -> presenter.englishMode = it
        }

        swKatakanaMode.setOnCheckedChangeListener {
            _, it -> presenter.katakanaMode = it
        }

        val listener = DialogInterface.OnClickListener {
            _, _ -> presenter.setAdapterMode()
        }

        val dialog = AlertDialog.Builder(requireContext())
                .setView(view)
                .setPositiveButton("Закрыть", listener)
                .show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initContent()
    }

    private fun initContent() {
        presenter.fillAdapter()
        setRecyclerViewParam()
        rvKana.addItemDecoration(ItemOffsetDecoration(4))
    }

    override fun setRecyclerViewParam() {
        rvKana.also {
            it.layoutManager = GridLayoutManager(activity, 5)
            it.setHasFixedSize(true)
            it.setItemViewCacheSize(30)
            it.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
            it.adapter = presenter.getAdapter()
        }
    }
}