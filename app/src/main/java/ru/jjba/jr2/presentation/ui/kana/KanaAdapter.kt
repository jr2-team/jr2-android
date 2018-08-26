package ru.jjba.jr2.presentation.ui.kana

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_kana.view.*
import ru.jjba.jr2.R
import ru.jjba.jr2.data.repository.kana.KanaRepository
import ru.jjba.jr2.domain.entity.Kana
import kotlin.properties.Delegates

class KanaAdapter(
        val kanaRepository: KanaRepository = KanaRepository()
) : RecyclerView.Adapter<KanaAdapter.ViewHolder>() {
    var kanaList: List<Kana> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_kana, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount() = kanaList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(kanaList[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(kana: Kana) = with(itemView) {
            tvKanaBig.text = kana.hiragana
            tvKanaLittle.text = kana.katakana
            tvKanaForeignSound.text = kana.rus
        }
    }
}