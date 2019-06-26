package io.github.jr2team.jr2android.presentation.ui.vocab.word.list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_word.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import io.github.jr2team.jr2android.R
import io.github.jr2team.jr2android.domain.entity.Word
import io.github.jr2team.jr2android.presentation.ui.util.inflate
import kotlin.properties.Delegates

/**
 * Адаптер для [RecyclerView] из [WordListFragment]
 */
class WordListAdapter : RecyclerView.Adapter<WordListAdapter.ViewHolder>() {
    // TODO: Использовать DiffUtil взамен notifyDataSetChanged
    var words: List<Word> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    var onItemClicked: ((Word) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(parent.inflate(R.layout.item_word), onItemClicked)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bindView(words[position], position)

    override fun getItemCount() = words.size

    inner class ViewHolder(
            itemView: View,
            onItemClicked: ((Word) -> Unit)? = null
    ) : RecyclerView.ViewHolder(itemView) {
        private lateinit var wordArg: Word

        init {
            itemView.onClick { onItemClicked?.invoke(wordArg) }
        }

        fun bindView(word: Word, position: Int) = with(itemView) {
            wordArg = word
            tvWordId.text = (position + 1).toString()
            // TODO: Сделать Функцию для форматирования текста с фуриганой
            tvBasicInterp.text = word.basicInterpretation
            tvJlptLevel.text = "N${word.jlptLevel}"
        }
    }
}