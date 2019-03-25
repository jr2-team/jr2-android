package ru.jjba.jr2.presentation.ui.number.view.quiz

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_number_practice_quiz.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.ctx
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.base.BaseFragment
import ru.jjba.jr2.presentation.ui.number.viewmodel.NumberPracticeSessionViewModel
import ru.jjba.jr2.presentation.ui.number.viewmodel.quiz.NumberPracticeQuizViewModel
import ru.jjba.jr2.presentation.viewmodel.util.InjectorUtil
import ru.jjba.jr2.presentation.viewmodel.util.observe

class NumberPracticeQuizFragment : BaseFragment<NumberPracticeQuizViewModel>() {
    private val sessionViewModel: NumberPracticeSessionViewModel by lazy {
        ViewModelProviders.of(act).get(NumberPracticeSessionViewModel::class.java)
    }
    override val layoutRes = R.layout.fragment_number_practice_quiz
    override val titleDefault: String
        get() = "Тест"

    private val adapter = NumbePracticeQuizAdapter()

    override fun initContent() {
        showBottomNavigation(false)
    }

    override fun injectViewModel() {
        val sessionRepository = sessionViewModel.getSessionRepository()
        val factory = InjectorUtil.provideNumberPracticeQuizViewModel(sessionRepository)
        viewModel = ViewModelProviders
            .of(this, factory)
            .get(NumberPracticeQuizViewModel::class.java)

        btnShowMarked.onClick { adapter.highlightMarked() }
        view?.onClick { viewModel.onNextQuiz() }
        rvVariants.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(ctx)
            adapter = this@NumberPracticeQuizFragment.adapter
        }
    }

    override fun observeData() = with(viewModel) {
        observe(viewModel.questionLiveData) { question ->
            tvNumberPracticeQuizAskedNumber.text = question
        }
        observe(viewModel.hintLiveData) { hint ->
            tvNumberPracticeQuizAskedNumberHint.text = hint
        }
        observe(viewModel.variantsLiveData) { variants ->
            adapter.variants = variants
        }
    }

    private fun prepeareViewForNextQuiz() {

    }
}