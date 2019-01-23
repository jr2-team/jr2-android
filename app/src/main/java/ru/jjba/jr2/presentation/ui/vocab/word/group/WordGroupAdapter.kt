package ru.jjba.jr2.presentation.ui.vocab.word.group

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import org.zakariya.stickyheaders.SectioningAdapter
import ru.jjba.jr2.R
import ru.jjba.jr2.domain.entity.Group
import ru.jjba.jr2.domain.select.SectionWithGroups
import kotlin.properties.Delegates.observable

class WordGroupAdapter : SectioningAdapter() {
    var sections: List<SectionWithGroups> by observable(emptyList()) { _, _, _ ->
        notifyAllSectionsDataSetChanged()
    }
    var onWordGroupItemClicked: ((Group) -> Unit)? = null

    override fun getNumberOfSections(): Int =
            sections.size

    override fun getNumberOfItemsInSection(idx: Int): Int =
            sections[idx].groups.size

    override fun doesSectionHaveHeader(sectionIndex: Int): Boolean =
            true

    override fun onCreateGhostHeaderViewHolder(parent: ViewGroup?): GhostHeaderViewHolder {
        val ghostView = View(parent?.context).apply {
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        }
        return GhostHeaderViewHolder(ghostView)
    }

    override fun onCreateItemViewHolder(
            parent: ViewGroup?,
            itemViewType: Int
    ): SectioningAdapter.ItemViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        return WordGroupItemViewHolder(
                inflater.inflate(R.layout.item_word_group, parent, false),
                onWordGroupItemClicked
        )
    }

    override fun onCreateHeaderViewHolder(
            parent: ViewGroup?,
            headerViewType: Int
    ): SectioningAdapter.HeaderViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        return WordGroupSectionViewHolder(
                inflater.inflate(R.layout.item_word_group_section, parent, false),
                ::getSectionForAdapterPosition,
                ::onWordGroupSectionClicked
        )
    }

    override fun onBindItemViewHolder(
            viewHolder: SectioningAdapter.ItemViewHolder?,
            sectionIndex: Int,
            itemIndex: Int,
            itemViewType: Int
    ) {
        (viewHolder as? WordGroupItemViewHolder)
                ?.bindView(sections[sectionIndex].groups[itemIndex])
    }

    override fun onBindHeaderViewHolder(
            viewHolder: SectioningAdapter.HeaderViewHolder?,
            sectionIndex: Int,
            headerViewType: Int
    ) {
        (viewHolder as? WordGroupSectionViewHolder)
                ?.bindView(sections[sectionIndex])
    }

    private fun onWordGroupSectionClicked(sectionIdx: Int) {
        setSectionIsCollapsed(sectionIdx, !isSectionCollapsed(sectionIdx))
    }
}