package ru.jjba.jr2.presentation.ui.kana.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_kana.view.*
import ru.jjba.jr2.R
import ru.jjba.jr2.domain.entity.Kana
import ru.jjba.jr2.presentation.navigation.NavigationHolder.router
import ru.jjba.jr2.presentation.navigation.Screen
import kotlin.properties.Delegates

class KanaAdapter : RecyclerView.Adapter<KanaAdapter.ViewHolder>() {

    var kanaList: List<Kana> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    var englishMode: Boolean by Delegates.observable(false) { _, _, _ ->
        notifyDataSetChanged()
    }

    var katakanaMode: Boolean by Delegates.observable(false) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_kana, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount() = kanaList.size/5

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position*5)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(startPosition: Int) = with(itemView) {
            if (!katakanaMode) {
                tvAKanaBig.text = kanaList[startPosition].hiragana
                tvAKanaLittle.text = kanaList[startPosition].katakana

                tvCKanaBig.text = kanaList[startPosition+2].hiragana
                tvCKanaLittle.text = kanaList[startPosition+2].katakana

                tvIKanaBig.text = kanaList[startPosition+4].hiragana
                tvIKanaLittle.text = kanaList[startPosition+4].katakana
            } else {
                tvAKanaBig.text = kanaList[startPosition].katakana
                tvAKanaLittle.text = kanaList[startPosition].hiragana

                tvCKanaBig.text = kanaList[startPosition+2].katakana
                tvCKanaLittle.text = kanaList[startPosition+2].hiragana

                tvIKanaBig.text = kanaList[startPosition+4].katakana
                tvIKanaLittle.text = kanaList[startPosition+4].hiragana
            }

            if (!englishMode) {
                tvAKanaForeignSound.text = kanaList[startPosition].rus
                tvCKanaForeignSound.text = kanaList[startPosition+2].rus
                tvIKanaForeignSound.text = kanaList[startPosition+4].rus
            } else {
                tvAKanaForeignSound.text = kanaList[startPosition].eng
                tvCKanaForeignSound.text = kanaList[startPosition+2].eng
                tvIKanaForeignSound.text = kanaList[startPosition+4].eng
            }

            cwAKana.setOnClickListener {
                router.navigateTo(Screen.KANA_DETAILS.title, kanaList[startPosition].id)
            }

            cwCKana.setOnClickListener {
                router.navigateTo(Screen.KANA_DETAILS.title, kanaList[startPosition+2].id)
            }

            cwIKana.setOnClickListener {
                router.navigateTo(Screen.KANA_DETAILS.title, kanaList[startPosition+4].id)
            }

            if(kanaList[startPosition+1].hiragana != "") {
                cwBKana.visibility = View.VISIBLE
                cwDKana.visibility = View.VISIBLE
                if (!katakanaMode) {
                    tvBKanaBig.text = kanaList[startPosition + 1].hiragana
                    tvBKanaLittle.text = kanaList[startPosition + 1].katakana

                    tvDKanaBig.text = kanaList[startPosition + 3].hiragana
                    tvDKanaLittle.text = kanaList[startPosition + 3].katakana
                } else {
                    tvBKanaBig.text = kanaList[startPosition + 1].katakana
                    tvBKanaLittle.text = kanaList[startPosition + 1].hiragana

                    tvDKanaBig.text = kanaList[startPosition+3].katakana
                    tvDKanaLittle.text = kanaList[startPosition+3].hiragana
                }

                if (!englishMode) {
                    tvBKanaForeignSound.text = kanaList[startPosition+1].rus
                    tvDKanaForeignSound.text = kanaList[startPosition+3].rus
                } else {
                    tvBKanaForeignSound.text = kanaList[startPosition+1].eng
                    tvDKanaForeignSound.text = kanaList[startPosition+3].eng
                }

                cwBKana.setOnClickListener {
                    if(kanaList[startPosition+1].hiragana != "")
                        router.navigateTo(Screen.KANA_DETAILS.title, kanaList[startPosition+1].id)
                }

                cwDKana.setOnClickListener {
                    if(kanaList[startPosition+3].hiragana != "")
                        router.navigateTo(Screen.KANA_DETAILS.title, kanaList[startPosition+3].id)
                }

            } else {
                cwBKana.visibility = View.INVISIBLE
                cwDKana.visibility = View.INVISIBLE
            }
        }
    }
}