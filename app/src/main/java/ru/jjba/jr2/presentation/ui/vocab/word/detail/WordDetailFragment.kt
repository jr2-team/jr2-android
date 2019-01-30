package ru.jjba.jr2.presentation.ui.vocab.word.detail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_word_detail.*
import kotlinx.android.synthetic.main.toolbar_nav_detail.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.selector
import org.jetbrains.anko.support.v4.toast
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseFragment
import ru.jjba.jr2.presentation.ui.util.NavDetail
import ru.jjba.jr2.presentation.viewmodel.vocab.word.WordDetailViewModel
import kotlin.random.Random

class WordDetailFragment : BaseFragment<WordDetailViewModel>() {
    override var viewModel = WordDetailViewModel()
    override val layoutRes = R.layout.fragment_word_detail
    override val titleDefault
        get() = ""

    private val args by navArgs<WordDetailFragmentArgs>()
    private var detailNavigatorState: String? by instanceState()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBottomNavigation(false)
        showMainToolbar(false)
        showNavDetailToolbar()
        viewModel.setArgs(args.wordId)
    }

    override fun initContent() {
        btnNavTo.onClick {
            val navToWordDetail = WordDetailFragmentDirections
                    .actionWordDetailFragmentSelf(Random.nextInt(1, 3000))
            findNavController().navigate(navToWordDetail)
        }
    }

    override fun observeData() = with(viewModel) {
        observeWord().observe(viewLifecycleOwner, Observer { word ->
            if (word == null) return@Observer
            tvWordJp.text = word.value
            detailNavigator.navigatedForward(NavDetail(word.value, "слово"))
            restoreInstanceState()
        })
        detailNavigator.getNavTitleLive().observe(viewLifecycleOwner, Observer {
            tbNavDetail.title = it
        })
        detailNavigator.observeNavDetails().observe(viewLifecycleOwner, Observer { navDetails ->
            tbNavDetail.onClick {
                if (navDetails.isEmpty()) return@onClick
                selector(
                        getString(R.string.word_detail_go_back_to),
                        navDetails
                ) { _, idx ->
                    val backTimes = (navDetails.size - 1) - idx
                    detailNavigator.navigatedBack(backTimes)
                    repeat(backTimes + 1) {
                        act.supportFragmentManager.popBackStack()
                    }
                }
            }
        })
    }

    override fun saveInstanceState() {
        detailNavigatorState = viewModel.detailNavigator.saveNavDetailsStateToJson()
    }

    private fun restoreInstanceState() {
        detailNavigatorState?.run {
            viewModel.detailNavigator.restoreNavDetailFromJson(this)
        }
    }

    private fun showNavDetailToolbar() {
        tbNavDetail.apply {
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                findNavController().popBackStack(R.id.wordListFragment, false)
            }
        }
    }
}