package ru.jjba.jr2.presentation.ui.vocab.word.group

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_word_group_header.view.*
import kotlinx.android.synthetic.main.item_word_group_item.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.zakariya.stickyheaders.SectioningAdapter
import ru.jjba.jr2.R
import kotlin.properties.Delegates.observable

class WordGroupAdapterCollapsed : SectioningAdapter() {
    class Section(
            val title: String,
            val items: List<String>
    )

    var sections: MutableList<Section> by observable(mutableListOf()) { _, _, _ ->
        notifyDataSetChanged()
    }

    var onItemClicked: ((String) -> Unit)? = null

    override fun getNumberOfSections(): Int = sections.size

    override fun getNumberOfItemsInSection(idx: Int): Int =
            sections[idx].items.size

    override fun doesSectionHaveHeader(sectionIndex: Int): Boolean = true

    override fun onCreateGhostHeaderViewHolder(parent: ViewGroup?): GhostHeaderViewHolder {
        val ghostView = View(parent?.context).apply {
            layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        return GhostHeaderViewHolder(ghostView)
    }

    override fun onCreateItemViewHolder(
            parent: ViewGroup?,
            itemViewType: Int
    ): SectioningAdapter.ItemViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        return ItemViewHolder(
                inflater.inflate(R.layout.item_word_group_item, parent, false),
                onItemClicked
        )
    }

    override fun onCreateHeaderViewHolder(
            parent: ViewGroup?,
            headerViewType: Int
    ): SectioningAdapter.HeaderViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        return HeaderViewHolder(inflater.inflate(R.layout.item_word_group_header, parent, false))
    }

    override fun onBindItemViewHolder(
            viewHolder: SectioningAdapter.ItemViewHolder?,
            sectionIndex: Int,
            itemIndex: Int,
            itemViewType: Int
    ) {
        (viewHolder as? ItemViewHolder)?.bindView(sections[sectionIndex].items[itemIndex])
    }

    override fun onBindHeaderViewHolder(
            viewHolder: SectioningAdapter.HeaderViewHolder?,
            sectionIndex: Int,
            headerViewType: Int
    ) {
        (viewHolder as? HeaderViewHolder)?.bindView(sections[sectionIndex])
    }

    private fun onToggleSectionCollapse(sectionIdx: Int) {
        setSectionIsCollapsed(sectionIdx, !isSectionCollapsed(sectionIdx))
    }

    fun onCollapseAllSections() {
        for (i in 0 until sections.size - 1) { setSectionIsCollapsed(i, true) }
    }

    inner class ItemViewHolder(
            private val view: View,
            onItemClicked: ((String) -> Unit)? = null
    ) : SectioningAdapter.ItemViewHolder(view) {
        private lateinit var wordGroupArg: String

        init {
            view.onClick { onItemClicked?.invoke(wordGroupArg) }
        }

        fun bindView(wordGroup: String) = with(view) {
            wordGroupArg = wordGroup

            tvWordGroup.text = wordGroup
        }
    }

    inner class HeaderViewHolder(
            private val view: View
    ) : SectioningAdapter.HeaderViewHolder(view) {
        fun bindView(section: Section) = with(view) {
            tvWordGroupHeaderTitle.text = section.title

            onClick {
                val sectionPosition = this@WordGroupAdapterCollapsed
                        .getSectionForAdapterPosition(adapterPosition)
                this@WordGroupAdapterCollapsed.onToggleSectionCollapse(sectionPosition)
            }
        }
    }
}