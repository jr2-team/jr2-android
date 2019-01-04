package ru.jjba.jr2.presentation.ui.vocab.word.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_word.view.*
import ru.jjba.jr2.R
import ru.jjba.jr2.domain.entity.Word
import kotlin.properties.Delegates

class WordAdapter : RecyclerView.Adapter<WordAdapter.ViewHolder>() {
    var words: List<Word> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_word, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount() = words.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(words[position], position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(word: Word, position: Int) = with(itemView) {
            tvWordId.text = (position + 1).toString()
            /**
             * TODO : Реализовать функцию для форматирования текста с фуриганой
             * вставка пробелов, чтобы влезала вся фуригана
             */
            ftvWord.setFuriganaText("<ruby>${word.wordJp}<rt>${word.wordFurigana}</rt></ruby>")
            tvBasicInterp.text = word.basicInterpretation
            tvJlptLevel.text = "N${word.jlptLevel}"

            this.setOnClickListener {
                //router.navigateTo(Screen.WORD_DETAILS.title, word.id)
            }
        }
    }
}