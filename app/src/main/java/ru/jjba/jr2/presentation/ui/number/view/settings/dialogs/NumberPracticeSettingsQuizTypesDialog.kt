package ru.jjba.jr2.presentation.ui.number.view.settings.dialogs

import android.app.AlertDialog
import android.app.Dialog
import androidx.lifecycle.ViewModelProviders
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.ctx
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.base.BaseDialog
import ru.jjba.jr2.presentation.ui.number.viewmodel.settings.NumberPracticeSettingsViewModel

class NumberPracticeSettingsQuizTypesDialog : BaseDialog() {
    override val layoutRes = R.layout.fragment_number_practice_settings_quiz_types

    private val viewModel: NumberPracticeSettingsViewModel by lazy {
        ViewModelProviders.of(act).get(NumberPracticeSettingsViewModel::class.java)
    }

    override fun buildDialog(): Dialog = AlertDialog.Builder(ctx).apply {
        setView(inflatedView)
        setPositiveButton("Принять") { _, _ ->
            viewModel.onSaveNumberQuizTypes("")
        }
        setNegativeButton("Закрыть") { _, _ ->

        }
        setNeutralButton("Сбросить") { _, _ ->

        }
    }.create()

    override fun initContent(): Unit = with(inflatedView) {

    }
}