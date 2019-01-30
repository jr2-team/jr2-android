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
import ru.jjba.jr2.presentation.ui.util.NavigationDetail
import ru.jjba.jr2.presentation.viewmodel.vocab.word.WordDetailViewModel
import kotlin.random.Random

class WordDetailFragment : BaseFragment<WordDetailViewModel>() {
    override var viewModel = WordDetailViewModel()
    override val layoutRes = R.layout.fragment_word_detail
    override val titleDefault
        get() = ""

    private val args by navArgs<WordDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBottomNavigation(false)
        showToolbar(false)
        setupCustomToolbar()
        viewModel.setArgs(args.wordId)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearData()
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
            detailNavigator.navigatedForward(NavigationDetail(word.value, "слово"))
            tvNavTitle.onClick {
                selector(
                        getString(R.string.word_detail_go_back_to),
                        detailNavigator.getNavigationDetails()
                ) { _, _ -> }
            }
        })
        observeNavTitle().observe(viewLifecycleOwner, Observer {
            tvNavTitle.text = it
        })
    }

    private fun setupCustomToolbar() {
        tbNavDetail.apply {
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                //viewModel.detailNavigator.navigatedOutOfDetail()
                findNavController().popBackStack(R.id.wordListFragment, false)
            }
        }
    }
}