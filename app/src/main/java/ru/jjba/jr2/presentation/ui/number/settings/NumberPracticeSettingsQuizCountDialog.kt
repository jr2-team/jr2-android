package ru.jjba.jr2.presentation.ui.number.settings

import android.app.AlertDialog
import android.app.Dialog
import android.widget.SeekBar
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_number_practice_settings_quizz_count.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.ctx
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.base.BaseDialog
import ru.jjba.jr2.presentation.viewmodel.number.settings.NumberPracticeSettingsViewModel
import ru.jjba.jr2.presentation.viewmodel.util.observe

class NumberPracticeSettingsQuizCountDialog : BaseDialog() {
    override val layoutRes: Int = R.layout.fragment_number_practice_settings_quizz_count

    private val viewModel: NumberPracticeSettingsViewModel by lazy {
        ViewModelProviders.of(act).get(NumberPracticeSettingsViewModel::class.java)
    }

    override fun buildDialog(): Dialog = AlertDialog.Builder(ctx).apply {
        setView(inflatedView)
        setPositiveButton("Принять") { _, _ ->
            viewModel.onSaveNumberQuizCount(
                    inflatedView.sbNumberPracticeQuizCountSetting.progress
            )
        }
        setNegativeButton("Закрыть") { _, _ ->

        }
        setNeutralButton("Сбросить") { _, _ ->

        }
    }.create()

    override fun initContent(): Unit = with(inflatedView) {
        btnNumberPracticeQuizCountMinus.onClick {
            if (sbNumberPracticeQuizCountSetting.progress != 1) {
                sbNumberPracticeQuizCountSetting.progress -= 1
            }
        }
        btnNumberPracticeQuizCountPlus.onClick {
            if (sbNumberPracticeQuizCountSetting.progress != 100) {
                sbNumberPracticeQuizCountSetting.progress += 1
            }
        }
        sbNumberPracticeQuizCountSetting.apply {
            // TODO: Move default max num quiz count to res
            max = 100
            observe(viewModel.numberQuizCountPref) { progress = it }
            setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onStartTrackingTouch(seekBar: SeekBar) {}
                override fun onStopTrackingTouch(seekBar: SeekBar) {}
                override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                    this@with.tvNumberPracticeQuizCountSetting.text = "$i тестов"
                }
            })
        }
    }
}