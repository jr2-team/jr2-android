package ru.jjba.jr2.presentation.ui.number.view.quiz

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_number_practice_quiz_variant.view.*
import org.jetbrains.anko.backgroundColor
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.util.inflate
import kotlin.properties.Delegates

class NumbePracticeQuizAdapter : RecyclerView.Adapter<NumbePracticeQuizAdapter.ViewHolder>() {
    var variants: List<String> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    var markAsRight: MutableList<(() -> Unit)> = mutableListOf()

    fun highlightMarked() {
        markAsRight.forEach { it.invoke() }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.item_number_practice_quiz_variant))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(variants[position])
        if (position % 2 == 0) {
            markAsRight.add(holder::mark)
        }
    }

    override fun getItemCount() = variants.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun mark() {
            itemView.backgroundColor = R.color.colorPrimary
        }

        fun bindView(text: String) {
            itemView.tvAbc.text = text
        }
    }
}