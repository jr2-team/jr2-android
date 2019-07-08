package io.github.jr2team.jr2android.presentation.vocabulary.word.group._view.list_adapter

import android.view.View
import io.github.jr2team.jr2android.domain.entity.Group
import io.github.jr2team.jr2android.presentation.vocabulary.word.group._viewmodel.WordGroupEvent
import kotlinx.android.synthetic.main.item_word_group.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.zakariya.stickyheaders.SectioningAdapter

class WordGroupItemViewHolder(
    private val view: View,
    onItemClicked: ((WordGroupEvent) -> Unit)? = null
) : SectioningAdapter.ItemViewHolder(view) {
    private lateinit var wordGroupArg: Group

    init {
        view.onClick {
            if (::wordGroupArg.isInitialized) {
                onItemClicked?.invoke(WordGroupEvent.OnWordGroupClicked(wordGroupArg))
            }
        }
    }

    fun bindView(wordGroup: Group) = with(view) {
        wordGroupArg = wordGroup
        tvWordGroupName.text = wordGroup.name
        tvWordsCount.text = "${wordGroup.itemsCount} слов"
    }
}