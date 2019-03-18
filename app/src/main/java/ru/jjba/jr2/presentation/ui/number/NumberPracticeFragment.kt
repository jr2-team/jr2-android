package ru.jjba.jr2.presentation.ui.number

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_number_practice.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseFragment
import ru.jjba.jr2.presentation.viewmodel.number.NumberPracticeViewModel
import ru.jjba.jr2.presentation.viewmodel.util.observe

class NumberPracticeFragment : BaseFragment<NumberPracticeViewModel>() {
    override var viewModel = NumberPracticeViewModel()
    override val layoutRes = R.layout.fragment_number_practice
    override val titleDefault
        get() = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBottomNavigation(false)
        view.onClick { viewModel.onNextClicked() }
    }

    override fun observeData() = with(viewModel) {
        observe(observeCorrectVariant()) {
            tvFirstTestProperty.text = it
        }
        observe(observeVariants()) {
            npvcAnswers.setAnswers(it)
        }
        observe(observeCorrectVariantFromVariants()) { correctVariant ->
            correctVariant?.let { npvcAnswers.revealCorrectVariant(it) }
        }
        observe(npvcAnswers.observeGivenAnswer()) {
            viewModel.onAnswerClicked(it)
        }
    }
}