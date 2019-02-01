package ru.jjba.jr2.presentation.ui.vocab.word.detail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_word_detail.*
import kotlinx.android.synthetic.main.toolbar_nav_detail.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.selector
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseFragment
import ru.jjba.jr2.presentation.viewmodel.shared.NavDetail
import ru.jjba.jr2.presentation.viewmodel.shared.NavDetailViewModel
import ru.jjba.jr2.presentation.viewmodel.util.observe
import ru.jjba.jr2.presentation.viewmodel.vocab.word.WordDetailViewModel
import kotlin.random.Random

class WordDetailFragment : BaseFragment<WordDetailViewModel>() {
    override var viewModel = WordDetailViewModel()
    override val layoutRes = R.layout.fragment_word_detail
    override val titleDefault
        get() = ""

    private val args by navArgs<WordDetailFragmentArgs>()

    private var navIdState: Long by instanceState(Random.nextLong(100000))
    private var navIsVisitedState: Boolean by instanceState(false)
    private var navDetailsState: String? by instanceState()
    private val navDetailViewModel: NavDetailViewModel by lazy {
        ViewModelProviders.of(act).get(NavDetailViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showBottomNavigation(false)
        showMainToolbar(false)
        viewModel.setArgs(args.wordId)
    }

    override fun initContent() {
        setupNavDetailToolbar()
        btnNavTo.onClick {
            val navToWordDetail = WordDetailFragmentDirections
                    .actionWordDetailFragmentSelf(Random.nextInt(1, 100))
            findNavController().navigate(navToWordDetail)
        }
    }

    override fun observeData() = with(viewLifecycleOwner) {
        observe(viewModel.observeWord()) { word ->
            if (word == null) return@observe
            tvWordJp.text = word.value
            tvWordBasicInterpretation.text = word.basicInterpretation
            if (!navIsVisitedState) {
                navDetailViewModel.onNavigatedForward(NavDetail(
                        word.value,
                        navIdState,
                        "(слово)"
                ))
            } else {
                navDetailsState?.run {
                    navDetailViewModel.onRestoreNavDetailsState(this)
                }
            }
        }
    }

    override fun saveInstanceState() {
        navIsVisitedState = true
        navDetailsState = navDetailViewModel.onSaveNavDetailsState()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun observeNavDetail() {
        observe(navDetailViewModel.getTitleLiveData()) {
            tbNavDetail.title = it
        }
        observe(navDetailViewModel.getDetailsLiveData()) { details ->
            tbNavDetail.onClick {
                if (details.isNullOrEmpty()) return@onClick
                selector(
                        getString(R.string.word_detail_go_back_to),
                        if (navIsVisitedState) {
                            details.subList(0, details.lastIndex)
                        } else {
                            details
                        }
                ) { _, i ->
                    val navBackTimes = details.size - i - if (navIsVisitedState) 1 else 0
                    navDetailViewModel.onNavigatedBack(navBackTimes)
                    repeat(navBackTimes) {
                        act.supportFragmentManager.popBackStack()
                    }
                }
            }
        }
    }

    private fun setupNavDetailToolbar() {
        tbNavDetail.removeCallbacks { }
        tbNavDetail.apply {
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                navDetailViewModel.onNavigatedOutOfDetail()
                findNavController().popBackStack(R.id.wordListFragment, false)
            }
        }
    }
}