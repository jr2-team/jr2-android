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
import kotlin.properties.Delegates

class WordAdapter(
        val interpretationDbRepository: InterpretationDbRepository = InterpretationDbRepository()
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
                val view = LayoutInflater.from(context).inflate(R.layout.fragment_word_details, null)
                val tvWordJp = view.findViewById<TextView>(R.id.tvWordDetailsJp)
                val tvWordFurigana = view.findViewById<TextView>(R.id.tvWorddetailsFurigana)
                val rvInterp = view.findViewById<RecyclerView>(R.id.rvInterp)

                tvWordJp.text = word.wordJp
                tvWordFurigana.text = word.wordFurigana

                val interpAdapter = ru.jjba.jr2.presentation.ui.interp.InterpAdapter()
                rvInterp.also {
                    it.setHasFixedSize(true)
                    it.layoutManager = LinearLayoutManager(context)
                    it.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
                    it.adapter = interpAdapter
                }

                interpretationDbRepository.getByWordId(word.id)
                        .first(kotlin.collections.emptyList())
                        .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                        .subscribeBy(
                                onSuccess = {
                                    interpAdapter.interpList = it
                                }
                        )

                val dialog = AlertDialog.Builder(context)
                        .setView(view)
                        .setPositiveButton("Close", null)
                        .show()

            }
        }
    }
}