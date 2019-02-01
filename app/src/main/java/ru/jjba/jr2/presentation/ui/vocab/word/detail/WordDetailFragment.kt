package ru.jjba.jr2.presentation.ui.vocab.word.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_word_detail.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.act
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseFragment
import ru.jjba.jr2.presentation.viewmodel.NavDetail
import ru.jjba.jr2.presentation.viewmodel.NavDetailViewModel
import ru.jjba.jr2.presentation.viewmodel.util.createFactory
import ru.jjba.jr2.presentation.viewmodel.util.observe
import ru.jjba.jr2.presentation.viewmodel.vocab.word.WordDetailViewModel
import kotlin.random.Random

class WordDetailFragment : BaseFragment<WordDetailViewModel>() {
    override var viewModel = WordDetailViewModel()
    override val layoutRes = R.layout.fragment_word_detail
    override val titleDefault
        get() = ""

    private val args by navArgs<WordDetailFragmentArgs>()
    private var fragmentId: Long by instanceState(Random.nextLong(100000))

    private var navDetailViewModel = NavDetailViewModel()
    private lateinit var toolbar: Toolbar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navDetailViewModel = ViewModelProviders
                .of(act, navDetailViewModel.createFactory())
                .get(navDetailViewModel::class.java)

        showBottomNavigation(false)
        showMainToolbar(true)
        toolbar = act.findViewById(R.id.tbMain)
        viewModel.setArgs(args.wordId)
    }

    override fun initContent() {
        toolbar.apply {
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                findNavController().popBackStack(R.id.wordListFragment, false)
            }
        }
        btnNavTo.onClick {
            val navToWordDetail = WordDetailFragmentDirections
                    .actionWordDetailFragmentSelf(Random.nextInt(1, 100))
            findNavController().navigate(navToWordDetail)
        }
    }

    override fun observeData() = with(viewModel) {
        observeWord().observe(viewLifecycleOwner, Observer { word ->
            if (word == null) return@Observer
            tvWordJp.text = word.value
            navDetailViewModel.onNavigatedForward(NavDetail(word.value, fragmentId))
            //navDetailViewModel.onNavigatedForward(NavDetail(word.value, fragmentId))
        })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun observeNavDetail() = with(act) {
        observe(navDetailViewModel.getTitleLiveData()) {
            toolbar.title = it
        }
        observe(navDetailViewModel.getLiveDetails) {
            val s = it
        }
    }
}