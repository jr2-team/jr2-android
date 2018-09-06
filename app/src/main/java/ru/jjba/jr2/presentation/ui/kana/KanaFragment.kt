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
import ru.jjba.jr2.domain.entity.Kana
import ru.jjba.jr2.presentation.presenters.kana.KanaPresenter
import ru.jjba.jr2.presentation.presenters.kana.KanaView
import ru.jjba.jr2.presentation.ui.base.BaseFragment
import ru.jjba.jr2.utils.ItemOffsetDecoration

class KanaFragment : BaseFragment(), KanaView {
    override val layoutRes: Int = R.layout.fragment_kana
    override val titleDefault: String
        get() = getString(R.string.kana_title)

    private var kanaAdapter = KanaAdapter()
    private var englishMode = false
    private var katakanaMode = false

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

    override fun setKanaList(list: List<Kana>) {
        if (list.isNotEmpty()) kanaAdapter.kanaList = list
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    private fun createSettingsDialog() {
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_kana_settings, null)
        val swEnglishMode = view.findViewById<Switch>(R.id.swEnglishMode)
        val swKatakanaMode = view.findViewById<Switch>(R.id.swKatakanaMode)

        swEnglishMode.isChecked = englishMode
        swKatakanaMode.isChecked = katakanaMode

        swEnglishMode.setOnCheckedChangeListener {
            _, it -> englishMode = it
        }

        swKatakanaMode.setOnCheckedChangeListener {
            _, it -> katakanaMode = it
        }

        val listener = DialogInterface.OnClickListener {
            _, _ -> setAdapterMode()
        }

        val dialog = AlertDialog.Builder(requireContext())
                .setView(view)
                .setPositiveButton("Закрыть", listener)
                .show()
    }

    private fun setAdapterMode() {
        kanaAdapter.englishMode = englishMode
        kanaAdapter.katakanaMode = katakanaMode
    }
}