package ru.jjba.jr2.presentation.ui.kana

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.SwitchCompat
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

    @InjectPresenter
    lateinit var presenter: KanaPresenter

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.kana_toolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        R.id.miSettings -> presenter.setSettingsDialog().let { true }
        else -> super.onOptionsItemSelected(item)
    }

    override fun createSettingsDialog(englishMode: Boolean, katakanaMode: Boolean) {
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_kana_settings, null)
        val swEnglishMode = view.findViewById<Switch>(R.id.swEnglishMode)
        val swKatakanaMode = view.findViewById<Switch>(R.id.swKatakanaMode)

        var eng = englishMode
        swEnglishMode.isChecked = eng
        var katakana = katakanaMode
        swKatakanaMode.isChecked = katakana

        swEnglishMode.setOnCheckedChangeListener {
            _, it -> eng = it
        }

        swKatakanaMode.setOnCheckedChangeListener {
            _, it -> katakana = it
        }

        val listener = DialogInterface.OnClickListener {
            _, _ ->
            setAdapterWithSettings(eng, katakana)
        }

        val dialog = AlertDialog.Builder(requireContext())
                .setView(view)
                .setPositiveButton("Сохранить", listener)
                .show()
    }

    open fun setAdapterWithSettings(englishMode: Boolean, katakanaMode: Boolean) {
        presenter.setAdapterMode(englishMode, katakanaMode)
        setRecyclerViewParam()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initContent()
    }

    private fun initContent() {
        setRecyclerViewParam()
        rvKana.addItemDecoration(ItemOffsetDecoration(4))
    }

    override fun setRecyclerViewParam() {
        rvKana.also {
            it.layoutManager = GridLayoutManager(activity, 5)
            it.adapter = presenter.getAdapter()
            it.adapter.notifyDataSetChanged()
        }
    }
}