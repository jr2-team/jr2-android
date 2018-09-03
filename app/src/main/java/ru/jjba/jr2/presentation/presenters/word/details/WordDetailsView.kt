package ru.jjba.jr2.presentation.presenters.word.details

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.jjba.jr2.domain.entity.Interp
import ru.jjba.jr2.domain.entity.Word

@StateStrategyType(AddToEndSingleStrategy::class)
interface WordDetailsView : MvpView {
    fun showWord(word: Word)

    fun showInterps(interps: List<Interp>)
}

