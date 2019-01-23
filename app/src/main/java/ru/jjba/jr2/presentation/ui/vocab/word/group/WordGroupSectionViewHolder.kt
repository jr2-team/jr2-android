package ru.jjba.jr2.presentation.ui.vocab.word.group

import android.view.View
import kotlinx.android.synthetic.main.item_word_group_section.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.zakariya.stickyheaders.SectioningAdapter

class WordGroupSectionViewHolder(
        private val view: View,
        getSectionPosition: ((Int) -> Int)? = null,
        onItemClicked: ((Int) -> Unit)? = null
) : SectioningAdapter.HeaderViewHolder(view) {
    init {
        view.onClick {
            getSectionPosition?.invoke(adapterPosition)?.let { sectionPosition ->
                onItemClicked?.invoke(sectionPosition)
            }
        }
    }

    fun bindView(section: WordGroupSection) = with(view) {
        tvWordGroupHeaderTitle.text = section.sectionTitle
    }
}