package ru.jjba.jr2.presentation.ui.word.list

import android.app.AlertDialog
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.item_word.view.*
import ru.jjba.jr2.R
import ru.jjba.jr2.data.repository.example.ExampleDbRepository
import ru.jjba.jr2.data.repository.interpretation.InterpretationDbRepository
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
        holder.bind(wordList[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(word: Word) = with(itemView) {
            tvWordJp.text = word.wordJp
            tvWordFurigana.text = word.wordFurigana
            tvJlptLevel.text = word.jlptLevel.toString()

            this.setOnClickListener {
                router.navigateTo(Screen.WORD_DETAILS.title, word.id)
            }
        }
    }
}