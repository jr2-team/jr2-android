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
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseFragment
import ru.jjba.jr2.presentation.ui.util.NavDetail
import ru.jjba.jr2.presentation.ui.util.isVisible
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
        showToolbar(false)
        setupCustomToolbar()
        viewModel.setArgs(args.wordId)
        restoreInstanceState()
    }

    override fun initContent() {
        btnNavTo.onClick {
            val navToWordDetail = WordDetailFragmentDirections
                    .actionWordDetailFragmentSelf(Random.nextInt(1, 3000))
            findNavController().navigate(navToWordDetail)
        }
        btnPop.onClick {
            act.supportFragmentManager.popBackStack()
            act.supportFragmentManager.popBackStack()
        }
    }

    override fun observeData() = with(viewModel) {
        observeWord().observe(viewLifecycleOwner, Observer { word ->
            if (word == null) return@Observer
            tvWordJp.text = word.value
            // TODO: Убрать во ViewModel
            detailNavigator.navigatedForward(NavDetail(word.value, "слово"))
            tvNavTitle.onClick {
                selector(
                        getString(R.string.word_detail_go_back_to),
                        detailNavigator.getNavigationDetails()
                ) { _, _ ->
                    // viewModel.onNavigateBack(times)
                }
            }
        })
        observeNavTitle().observe(viewLifecycleOwner, Observer {
            tvNavTitle.text = it
        })
    }

    override fun saveInstanceState() {
        detailNavigatorState = viewModel.detailNavigator.saveNavDetailsStateToJson()
    }

    private fun restoreInstanceState() {
        detailNavigatorState?.let {
            viewModel.detailNavigator.restoreNavDetailFromJson(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearData()
    }

    private fun setupCustomToolbar() {
        tbNavDetail.apply {
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                tbNavDetail.isVisible = false
                findNavController().popBackStack(R.id.wordListFragment, false)
            }
        }
    }
}