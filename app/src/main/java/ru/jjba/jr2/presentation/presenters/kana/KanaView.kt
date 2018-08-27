package ru.jjba.jr2.presentation.presenters.kana

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.jjba.jr2.domain.entity.Kana

@StateStrategyType(AddToEndSingleStrategy::class)
interface KanaView : MvpView {
    fun setRecyclerViewParam()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun createSettingsDialog(englishMode: Boolean, katakanaMode: Boolean)
}