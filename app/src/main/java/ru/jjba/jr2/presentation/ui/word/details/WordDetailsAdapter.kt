package ru.jjba.jr2.presentation.ui.word.details

import android.os.Build
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.zakariya.stickyheaders.SectioningAdapter
import ru.jjba.jr2.App
import ru.jjba.jr2.R
import ru.jjba.jr2.domain.entity.Interp
import ru.jjba.jr2.domain.entity.Word
import se.fekete.furiganatextview.furiganaview.FuriganaTextView

class WordDetailsAdapter(val sections: MutableList<Section>) : SectioningAdapter() {

    open class Item(open val type: Int)

    class ItemWord(val word: Word, override val type: Int = ITEM_TYPE_WORD) : Item(type)

    class ItemInterp(val interp: Interp, override val type: Int = ITEM_TYPE_INTERP) : Item(type)

    class Section(val type: Int, val title: String, val items: MutableList<Item>)

    override fun getNumberOfSections() = sections.size

    override fun getNumberOfItemsInSection(sectionIndex: Int) = sections[sectionIndex].items.size

    override fun doesSectionHaveHeader(sectionIndex: Int) = true

    override fun doesSectionHaveFooter(sectionIndex: Int) = false

    override fun getSectionHeaderUserType(sectionIndex: Int) = sections[sectionIndex].type

    override fun getSectionItemUserType(sectionIndex: Int, itemIndex: Int) =
            sections[sectionIndex].items[itemIndex].type

    override fun onCreateGhostHeaderViewHolder(parent: ViewGroup?): GhostHeaderViewHolder {
        val ghostView = View(parent?.context)
        ghostView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        return GhostHeaderViewHolder(ghostView)
    }

    override fun onCreateItemViewHolder(parent: ViewGroup?, itemType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent?.context)

        when (itemType) {
            ITEM_TYPE_WORD ->
                return ItemWordViewHolder(inflater.inflate(R.layout.item_word_detail_item_main, parent, false))
            ITEM_TYPE_INTERP ->
                return ItemInterpViewHolder(inflater.inflate(R.layout.item_word_detail_item_interp, parent, false))
        }

        throw IllegalArgumentException("Unrecognized itemType: $itemType")
    }

    override fun onCreateHeaderViewHolder(parent: ViewGroup?, headerUserType: Int): HeaderViewHolder {
        val inflater = LayoutInflater.from(parent?.context)

        when (headerUserType) {
            HEADER_TYPE_FUNCTIONAL ->
                return HeaderFunctionalViewHolder(inflater.inflate(R.layout.item_word_detail_functional, parent, false))
            HEADER_TYPE_SECTION ->
                return HeaderSectionViewHolder(inflater.inflate(R.layout.item_word_detail_header, parent, false))
        }

        throw IllegalArgumentException("Unrecognized headerType: $headerUserType")
    }

    override fun onBindItemViewHolder(
            viewHolder: ItemViewHolder?,
            sectionIndex: Int,
            itemIndex: Int,
            itemType: Int
    ) {
        val section = sections[sectionIndex]

        when (itemType) {
            ITEM_TYPE_WORD -> {
                val ivh = viewHolder as ItemWordViewHolder
                val word = (section.items[itemIndex] as ItemWord).word
                ivh.ftvWord.setFuriganaText("<ruby>${word.wordJp}<rt>${word.wordFurigana}</rt></ruby>")
                ivh.tvJlptLevel.text = "N${word.jlptLevel}"
                ivh.tvBasicInterp.text = word.basicInterp
            }
            ITEM_TYPE_INTERP -> {
                val ivh = viewHolder as ItemInterpViewHolder
                val interp = (section.items[itemIndex] as ItemInterp).interp
                ivh.tvInterp.text = interp.interp
                ivh.tvPos.text = interp.pos
            }
            else -> throw IllegalArgumentException("Unrecognized itemType: $itemType")
        }
    }

    override fun onBindHeaderViewHolder(
            viewHolder: HeaderViewHolder?,
            sectionIndex: Int,
            headerType: Int
    ) {
        val section = sections[sectionIndex]

        when (headerType) {
            HEADER_TYPE_FUNCTIONAL -> {
                val hvh = viewHolder as HeaderFunctionalViewHolder
                hvh.textView.text = section.title
            }
            HEADER_TYPE_SECTION -> {
                val hvh = viewHolder as HeaderSectionViewHolder
                hvh.textView.text = section.title
            }
            else -> throw IllegalArgumentException("Unrecognized headerType: $headerType")
        }
    }

    //  ======= ViewHolders =======
    class HeaderFunctionalViewHolder(itemView: View?) : SectioningAdapter.HeaderViewHolder(itemView) {
        var textView: TextView = itemView!!.findViewById(R.id.text)
    }

    class HeaderSectionViewHolder(itemView: View?) : SectioningAdapter.HeaderViewHolder(itemView) {
        var textView: TextView = itemView!!.findViewById(R.id.text)
    }

    class ItemWordViewHolder(itemView: View?) : SectioningAdapter.ItemViewHolder(itemView) {
        var ftvWord: FuriganaTextView = itemView!!.findViewById(R.id.ftvWord)
        var tvJlptLevel: TextView = itemView!!.findViewById(R.id.tvJlptLevel)
        var tvBasicInterp: TextView = itemView!!.findViewById(R.id.tvBasicInterp)

        private fun speakOut(text: String) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                App.instance.tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
            }
        }

        init {
            ftvWord.setOnClickListener { speakOut((it as TextView).text.toString()) }
            tvBasicInterp.setOnClickListener { speakOut((it as TextView).text.toString()) }
        }
    }

    class ItemInterpViewHolder(itemView: View?) : SectioningAdapter.ItemViewHolder(itemView) {
        var tvInterp: TextView = itemView!!.findViewById(R.id.tvInterp)
        var tvPos: TextView = itemView!!.findViewById(R.id.tvPos)
    }
    //  ======= =========== =======

    companion object {
        val HEADER_TYPE_FUNCTIONAL = 0
        val HEADER_TYPE_SECTION = 1

        val ITEM_TYPE_WORD = 0
        val ITEM_TYPE_INTERP = 1
    }
}