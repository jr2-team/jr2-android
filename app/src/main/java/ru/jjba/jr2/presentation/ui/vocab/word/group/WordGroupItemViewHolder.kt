package ru.jjba.jr2.presentation.ui.vocab.word.group

import android.view.View
import kotlinx.android.synthetic.main.item_word_group.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.zakariya.stickyheaders.SectioningAdapter
import ru.jjba.jr2.domain.entity.Group

class WordGroupItemViewHolder(
        private val view: View,
        onItemClicked: ((Group) -> Unit)? = null
) : SectioningAdapter.ItemViewHolder(view) {
    private lateinit var wordGroupArg: Group

    init {
        view.onClick {
            if (::wordGroupArg.isInitialized) onItemClicked?.invoke(wordGroupArg)
        }
    }

    fun bindView(wordGroup: Group) = with(view) {
        wordGroupArg = wordGroup
        tvWordGroupName.text = wordGroup.name
        tvWordsCount.text = "${wordGroup.itemsCount} слов"
    }
}