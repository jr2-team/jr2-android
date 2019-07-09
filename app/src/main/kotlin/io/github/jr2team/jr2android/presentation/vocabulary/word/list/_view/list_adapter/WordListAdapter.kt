package io.github.jr2team.jr2android.presentation.vocabulary.word.list._view.list_adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.jr2team.jr2android.R
import io.github.jr2team.jr2android.common.extensions.inflate
import io.github.jr2team.jr2android.domain.room_entities.entity.Word
import io.github.jr2team.jr2android.presentation.vocabulary.word.list._viewmodel.WordListEvent
import kotlinx.android.synthetic.main.item_word.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import kotlin.properties.Delegates

/**
 * Адаптер для [RecyclerView] из [WordListFragment]
 */
class WordListAdapter : RecyclerView.Adapter<WordListAdapter.ViewHolder>() {
    // TODO: Использовать DiffUtil вместо notifyDataSetChanged
    var words: List<Word> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    var onItemClicked: ((WordListEvent.OnWordClicked) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.item_word), onItemClicked)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindView(words[position], position)

    override fun getItemCount() = words.size

    inner class ViewHolder(
        itemView: View,
        onItemClicked: ((WordListEvent.OnWordClicked) -> Unit)? = null
    ) : RecyclerView.ViewHolder(itemView) {
        private lateinit var wordArg: Word

        init {
            itemView.onClick { onItemClicked?.invoke(WordListEvent.OnWordClicked(wordArg)) }
        }

        fun bindView(word: Word, position: Int) = with(itemView) {
            wordArg = word
            tvWordId.text = (position + 1).toString()
            // TODO: Функция для форматирования текста с фуриганой
            tvBasicInterp.text = word.basicInterpretation
            tvJlptLevel.text = "N${word.jlptLevel}"
        }
    }
}