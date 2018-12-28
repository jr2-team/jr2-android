package ru.jjba.jr2.presentation.ui.interpritatinos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_word_detail_item_interp.view.*
import ru.jjba.jr2.R
import ru.jjba.jr2.domain.entity.Interpretation
import kotlin.properties.Delegates

class InterpritationAdapter() : RecyclerView.Adapter<InterpritationAdapter.ViewHolder>() {
    var interpList: List<Interpretation> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_word_detail_item_interp, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount() = interpList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(interpList[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(interpretation: Interpretation) = with(itemView) {
            tvInterp.text = interpretation.interp
            tvPos.text = interpretation.pos

            setOnClickListener {

            }
        }
    }
}