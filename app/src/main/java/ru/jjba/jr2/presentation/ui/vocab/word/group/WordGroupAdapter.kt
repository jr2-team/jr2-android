package ru.jjba.jr2.presentation.ui.vocab.word.group

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_word_group.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import ru.jjba.jr2.R
import kotlin.properties.Delegates.observable

/**
 * Адаптер для [RecyclerView] для [WordGroupFragment]
 */
class WordGroupAdapter : RecyclerView.Adapter<WordGroupAdapter.ViewHolder>() {

    var onItemClicked: ((String) -> Unit)? = null

    var wordGroups: List<String> by observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_word_group, parent, false)
        return ViewHolder(view, onItemClicked)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(wordGroups[position])
    }

    override fun getItemCount() = wordGroups.size

    inner class ViewHolder(
            itemView: View,
            onItemClicked: ((String) -> Unit)? = null
    ) : RecyclerView.ViewHolder(itemView) {
        private lateinit var wordGroup: String

        init {
            itemView.onClick { onItemClicked?.invoke(wordGroup) }
        }

        fun bindView(wordGroup: String) {
            this.wordGroup = wordGroup
            itemView.tvWordGroup.text = wordGroup
        }
    }
}