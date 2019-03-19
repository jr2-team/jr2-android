package ru.jjba.jr2.presentation.ui.number.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_number_practice_settings_quizz_count.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.ctx
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.viewmodel.number.NumberPracticeSettingsViewModel
import ru.jjba.jr2.presentation.viewmodel.util.observe

class NumberPracticeSettingsQuizCountDialog : DialogFragment() {
    private lateinit var inflatedView: View
    private val viewModel: NumberPracticeSettingsViewModel by lazy {
        ViewModelProviders.of(act).get(NumberPracticeSettingsViewModel::class.java)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        inflatedView = act.layoutInflater.inflate(
                R.layout.fragment_number_practice_settings_quizz_count,
                null
        ) ?: throw IllegalStateException()

        return AlertDialog.Builder(ctx).apply {
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
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = with(inflatedView) {
        btnNumberPracticeQuizCountMinus.onClick {
            if (sbNumberPracticeQuizCountSetting.progress != 0) {
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
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}