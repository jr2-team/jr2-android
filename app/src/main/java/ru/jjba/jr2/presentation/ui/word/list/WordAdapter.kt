package ru.jjba.jr2.presentation.ui.word.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_word.view.*
import ru.jjba.jr2.R
import ru.jjba.jr2.domain.entity.Word
import ru.jjba.jr2.presentation.navigation.DefaultRouter
import ru.jjba.jr2.presentation.navigation.NavigationHolder
import ru.jjba.jr2.presentation.navigation.Screen
import kotlin.properties.Delegates

class WordAdapter(
        private val router: DefaultRouter = NavigationHolder.router
) : RecyclerView.Adapter<WordAdapter.ViewHolder>() {
    var wordList: List<Word> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_word, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount() = wordList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(wordList[position], position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(word: Word, position: Int) = with(itemView) {
            tvWordId.text = (position + 1).toString()
            /* TODO : Заимплементить функцию для форматирования текста с фуриганой
             * вставка пробелов, чтобы влезала вся фуригана
             */
            ftvWord.setFuriganaText("<ruby>${word.wordJp}<rt>${word.wordFurigana}</rt></ruby>")
            tvBasicInterp.text = word.basicInterp
            tvJlptLevel.text = "N${word.jlptLevel}"

            this.setOnClickListener {
                router.navigateTo(Screen.WORD_DETAILS.title, word.id)
            }
        }
    }
}