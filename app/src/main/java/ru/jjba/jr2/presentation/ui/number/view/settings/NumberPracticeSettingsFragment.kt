package ru.jjba.jr2.presentation.ui.number.view.settings

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_number_practice_settings.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.act
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.base.BaseFragment
import ru.jjba.jr2.presentation.ui.number.viewmodel.NumberPracticeSessionViewModel
import ru.jjba.jr2.presentation.ui.number.viewmodel.settings.NumberPracticeSettingsViewModel
import ru.jjba.jr2.presentation.ui.util.isVisible
import ru.jjba.jr2.presentation.viewmodel.util.InjectorUtil
import ru.jjba.jr2.presentation.viewmodel.util.observe

class NumberPracticeSettingsFragment : BaseFragment<NumberPracticeSettingsViewModel>() {
    private val sessionViewModel: NumberPracticeSessionViewModel by lazy {
        ViewModelProviders.of(act).get(NumberPracticeSessionViewModel::class.java)
    }
    override val layoutRes: Int = R.layout.fragment_number_practice_settings
    override val titleDefault: String
        get() = "Практика чтения чисел"

    override fun injectViewModel() {
        val sharedPreferences = act.getPreferences(Context.MODE_PRIVATE)
        val factory = InjectorUtil.provideNumberPracticeSettingsViewModel(sharedPreferences)
        viewModel = ViewModelProviders
                .of(act, factory)
                .get(NumberPracticeSettingsViewModel::class.java)
    }

    override fun initContent() {
        showBottomNavigation(false)

        btnNumberPracticeQuizStart.onClick { sessionViewModel.initSession() }
    }

    override fun observeData() = with(this) {
        observe(sessionViewModel.observeIsLoading()) { isSessionLoading ->
            pbNumberPracticeQuizIsLoading.isVisible = isSessionLoading
        }
        observe(sessionViewModel.observeNavToQuiz()) { direction ->
            findNavController().navigate(direction)
        }
    }

    companion object {
        private const val dialogSettingsQuizCountTag = "DIALOG_SETTINGS_QUIZ_COUNT"
        private const val dialogSettingsQuizTypesTag = "DIALOG_SETTINGS_QUIZ_TYPES"
    }
}