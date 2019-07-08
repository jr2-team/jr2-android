package io.github.jr2team.jr2android.presentation.vocabulary.word.group._view.list_adapter

import android.view.View
import io.github.jr2team.jr2android.domain.room.select.SectionWithGroups
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
            getSectionPosition?.invoke(adapterPosition)?.run {
                onItemClicked?.invoke(this)
            }
        }
    }

    fun bindView(sectionWithGroups: SectionWithGroups) = with(view) {
        tvWordGroupHeaderTitle.text = sectionWithGroups.section.name
    }
}