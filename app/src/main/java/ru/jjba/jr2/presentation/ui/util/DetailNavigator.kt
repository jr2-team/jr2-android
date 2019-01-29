package ru.jjba.jr2.presentation.ui.util

import android.text.SpannableStringBuilder
import android.text.style.RelativeSizeSpan
import org.jetbrains.anko.append

data class NavigationDetail(
        val title: String/*,
        val position: String = "",
        val detailType: String ="",
        val spannable: SpannableStringBuilder*/
)

class DetailNavigator(
        private val items: MutableList<NavigationDetail> = mutableListOf()
) {
    // TODO: Возвращать LiveData
    fun getFullTitle(): SpannableStringBuilder {
        if (items.isEmpty()) {
            return SpannableStringBuilder("!!!")
        }
        val spannable = SpannableStringBuilder(items.last().title)
        for (i in items.size - 2 downTo 1) {
            spannable.append("${items[i].title} < ", textSizeOfPrevious)
        }
        if (items.size >= 3) {
            spannable.append("${items.first().title}", textSizeOfPrevious)
        } else if (items.size == 2) {
            spannable.append("< ${items.first().title}", textSizeOfPrevious)
        }
        return spannable
    }

    fun getListOfnavigation(): List<String> =
        items.map {
            "i) ${it.title} (word/kanji)"
        }

    fun navigatedForward(detail: NavigationDetail) {
        items.update(detail)
    }

    fun navigatedBack(times: Int = 1) {
        if (items.isNotEmpty()) {
            items.removeAt(items.lastIndex)
        }
    }

    fun navigatedOutOfDetail() {
        items.clear()
    }

    companion object {
        private val textSizeOfPrevious = RelativeSizeSpan(0.5f)
    }
}

fun <T> MutableList<T>.update(item: T) {
    if (!this.contains(item)) {
        this.add(item)
    }
}