package ru.jjba.jr2.presentation.ui.vocab.word.detail

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_word_detail.*
import kotlinx.coroutines.selects.select
import org.jetbrains.anko.noButton
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.selector
import org.jetbrains.anko.yesButton
import ru.jjba.jr2.App
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
    // TODO : Перенести все связанное с наивгацией во ViewModel
    private val detailNavigator = App.instance.detailNavigator

    private lateinit var navigationTitle: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBottomNavigation(false)
        viewModel.setArgs(args.wordId)

        btnNavTo.onClick {
            findNavController().navigate(
                    WordDetailFragmentDirections
                            .actionWordDetailFragmentSelf(Random.nextInt(1, 3000))
            )
        }
        btnPop.onClick {
            act.supportFragmentManager.popBackStack()
            act.supportFragmentManager.popBackStack()
        }

        navigationTitle = act.findViewById(R.id.tvNavigationTitle)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        android.R.id.home -> {
            detailNavigator.navigatedOutOfDetail()
            findNavController().popBackStack(R.id.wordListFragment, false)
            true
        }
        else -> false
    }

    override fun initContent() {
        viewModel.fetchData()
    }

    override fun observeData() = with(viewModel) {
        observeWord().observe(viewLifecycleOwner, Observer { word ->
            tvWordJp.text = word.value
            detailNavigator.apply {
                navigatedForward(NavigationDetail(word.value))
                navigationTitle.text = getFullTitle()
                navigationTitle.onClick {
                    selector("Перейти обратно к...", getListOfnavigation()) { _, _ -> }
                }
            }
        })
    }
}