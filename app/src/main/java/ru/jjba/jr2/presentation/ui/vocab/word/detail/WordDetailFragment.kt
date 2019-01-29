package ru.jjba.jr2.presentation.ui.vocab.word.detail

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_word_detail.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.act
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

    var save: String by instanceState(String())

    private val args by navArgs<WordDetailFragmentArgs>()
    private val detailNavigator = App.instance.detailNavigator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBottomNavigation(false)
        viewModel.setArgs(args.wordId)

        btnNavTo.onClick {
            val d = WordDetailFragmentDirections.
                    actionWordDetailFragmentSelf(Random.nextInt(1, 3000))
            findNavController().navigate(d)
        }
        btnPop.onClick {
            act.supportFragmentManager.popBackStack()
            act.supportFragmentManager.popBackStack()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        android.R.id.home -> {
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
                setTitle(getFullTitle())
            }
        })
    }
}