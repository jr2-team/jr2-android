package ru.jjba.jr2.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.toolbar_nav_detail.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.selector
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.viewmodel.BaseViewModel
import ru.jjba.jr2.presentation.viewmodel.shared.NavDetail
import ru.jjba.jr2.presentation.viewmodel.shared.NavDetailViewModel
import ru.jjba.jr2.presentation.viewmodel.util.observe
import kotlin.random.Random

abstract class NavDetailFragment<VT : BaseViewModel> : BaseFragment<VT>() {
    private val navDetailViewModel: NavDetailViewModel by lazy {
        ViewModelProviders.of(act).get(NavDetailViewModel::class.java)
    }

    internal var fragmentIdState: Long by instanceState(Random.nextLong(100000))
    private var isVisitedState: Boolean by instanceState(false)
    private var navDetailsState: String? by instanceState()

    internal fun contentLoadingDone(navDetail: NavDetail) {
        if (!isVisitedState) {
            navDetailViewModel.onNavigatedForward(navDetail)
        } else {
            navDetailsState?.run {
                navDetailViewModel.onRestoreNavDetailsState(this)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBottomNavigation(false)
        showMainToolbar(false)
        setupNavDetailToolbar()
    }

    override fun saveInstanceState() {
        isVisitedState = true
        navDetailsState = navDetailViewModel.onSaveNavDetailsState()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun observeNavDetail() {
        observe(navDetailViewModel.getTitleLiveData()) { navTitle ->
            // TODO: Тайтл не отображается после восстановления инстанса с диска
            tbNavDetail.title = navTitle
        }
        observe(navDetailViewModel.getDetailsLiveData()) { details ->
            // TODO: Переделать во что-нибудь более адекватное
            tbNavDetail.onClick {
                if (details.isNullOrEmpty() || details.size == 1) {
                    return@onClick
                }
                selector(
                        getString(R.string.word_detail_go_back_to),
                        details.subList(0, details.lastIndex)
                ) { _, i ->
                    val navBackTimes = details.size - i - 1
                    navDetailViewModel.onNavigatedBack(navBackTimes)
                    repeat(navBackTimes) { act.supportFragmentManager.popBackStack() }
                }
            }
        }
    }

    private fun setupNavDetailToolbar() = with(tbNavDetail) {
        setNavigationIcon(R.drawable.ic_arrow_back)
        setNavigationOnClickListener {
            navDetailViewModel.onNavigatedOutOfDetail()
            findNavController().popBackStack(R.id.wordListFragment, false)
        }
    }
}