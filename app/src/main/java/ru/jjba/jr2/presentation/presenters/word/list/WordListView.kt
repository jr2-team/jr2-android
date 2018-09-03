package ru.jjba.jr2.presentation.presenters.word.list

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.jjba.jr2.domain.entity.Word

@StateStrategyType(AddToEndSingleStrategy::class)
interface WordListView : MvpView {
    fun showWordList(wordList: List<Word>)
}
