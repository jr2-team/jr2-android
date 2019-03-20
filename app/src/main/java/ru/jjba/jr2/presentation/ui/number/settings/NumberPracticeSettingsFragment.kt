package ru.jjba.jr2.presentation.ui.number.settings

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_number_practice_settings.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.act
import ru.jjba.jr2.R
import ru.jjba.jr2.domain.pojo.NumberPracticeQuizType
import ru.jjba.jr2.presentation.ui.base.BaseFragment
import ru.jjba.jr2.presentation.viewmodel.number.NumberPracticeSharedViewModel
import ru.jjba.jr2.presentation.viewmodel.number.settings.NumberPracticeSettingsViewModel
import ru.jjba.jr2.presentation.viewmodel.util.InjectorUtil
import ru.jjba.jr2.presentation.viewmodel.util.observe

class NumberPracticeSettingsFragment : BaseFragment<NumberPracticeSettingsViewModel>() {
    private val sharedViewModel: NumberPracticeSharedViewModel by lazy {
        ViewModelProviders.of(act).get(NumberPracticeSharedViewModel::class.java)
    }

    override val layoutRes: Int = R.layout.fragment_number_practice_settings
    override val titleDefault: String
        get() = "Практика чтения цифр"

    override fun injectViewModel() {
        val sharedPreferences = act.getPreferences(Context.MODE_PRIVATE)
        val factory = InjectorUtil.provideNumberPracticeSettingsViewModel(sharedPreferences)
        viewModel = ViewModelProviders
                .of(act, factory)
                .get(NumberPracticeSettingsViewModel::class.java)
    }

    override fun initContent() {
        btnNumberPracticeQuizCountSetting.onClick {
            NumberPracticeSettingsQuizCountDialog().show(fragmentManager, dialogSettingsQuizCountTag)
        }
        btnNumberPracticeQuizTypesSetting.onClick {
            NumberPracticeSettingsQuizTypesDialog().show(fragmentManager, dialogSettingsQuizTypesTag)
        }
        btnNumberPracticeQuizOtherSetting.onClick {

        }
        btnNumberPracticeQuizStart.onClick {
            sharedViewModel.onStartPracticeSession()
            val direction = NumberPracticeSettingsFragmentDirections.actionNumberPracticeSettingsFragmentToNumberPracticeQuizzFragment()
            findNavController().navigate(direction)
        }
    }

    override fun observeData() = with(this) {
        /*observe(viewModel.numberQuizCountPref) {
            btnNumberPracticeQuizCountSetting.text = it.toString()
        }*/
        //btnNumberPracticeQuizTypesSetting.text = "Типы тестов: "

    }

    companion object {
        private const val dialogSettingsQuizCountTag = "DIALOG_SETTINGS_QUIZ_COUNT"
        private const val dialogSettingsQuizTypesTag = "DIALOG_SETTINGS_QUIZ_TYPES"
    }
}