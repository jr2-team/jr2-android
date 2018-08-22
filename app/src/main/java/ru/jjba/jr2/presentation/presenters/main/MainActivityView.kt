package ru.jjba.jr2.presentation.presenters.main

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.jjba.jr2.domain.entity.Word

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainActivityView : MvpView {

    fun setRecyclerViewParam()

    fun setWordAdapter(words: List<Word>)

    fun showMessage(mes: String)
}
