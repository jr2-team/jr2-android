package ru.jjba.jr2.presentation.ui.number.fragment

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_number_practice_settings.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.act
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseFragment
import ru.jjba.jr2.presentation.ui.number.dialog.NumberPracticeSettingsQuizCountDialog
import ru.jjba.jr2.presentation.viewmodel.number.NumberPracticeSettingsViewModel
import ru.jjba.jr2.presentation.viewmodel.number.NumberPractiveSharedViewModel
import ru.jjba.jr2.presentation.viewmodel.util.observe

class NumberPracticeSettingsFragment : BaseFragment<NumberPracticeSettingsViewModel>() {
    private val sharedViewModel: NumberPractiveSharedViewModel by lazy {
        ViewModelProviders.of(act).get(NumberPractiveSharedViewModel::class.java)
    }

    override val layoutRes: Int = R.layout.fragment_number_practice_settings
    override val titleDefault: String
        get() = "Практика чтения цифр"

    override fun injectViewModel() {
        val sharedPreferences = act.getPreferences(Context.MODE_PRIVATE)
        val factory: ViewModelProvider.Factory? = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>) =
                    NumberPracticeSettingsViewModel(sharedPreferences) as T
        }
        viewModel = ViewModelProviders
                .of(act, factory)
                .get(NumberPracticeSettingsViewModel::class.java)
    }

    override fun initContent() {
        btnNumberPracticeQuizCountSetting.onClick {
            NumberPracticeSettingsQuizCountDialog().show(fragmentManager, "")
        }
    }

    override fun observeData() = with(this) {
        observe(viewModel.numberQuizCountPref) {
            btnNumberPracticeQuizCountSetting.text = it.toString()
        }
    }
}