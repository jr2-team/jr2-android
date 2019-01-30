package ru.jjba.jr2.presentation.ui.util

import android.text.SpannableStringBuilder
import android.text.Spanned.SPAN_EXCLUSIVE_INCLUSIVE
import android.text.style.RelativeSizeSpan
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

data class NavigationDetail(
        val title: String,
        val fragmentType: String = "слово"
)

class DetailNavigator {
    private val navDetails = mutableListOf<NavigationDetail>()
    private val navTitle = MutableLiveData<SpannableStringBuilder>()

    fun observeNavTitle(): LiveData<SpannableStringBuilder> = navTitle
    //fun observeNavDetails(): LiveData<List<String>> =

    // TODO: Возвращать LiveData
    fun getNavigationDetails(): List<String> =
            navDetails.mapIndexed { idx, detail ->
                "${idx + 1}) ${detail.title} (${detail.fragmentType})"
            }

    fun navigatedForward(detail: NavigationDetail) {
        // TODO: Придумать, как правильно проверять: слово А -> кндзи Б -> слово А
        if (!navDetails.contains(detail)) {
            navDetails.add(detail)
            rebuildNavTitle()
        }
    }

    // TODO: Переписать
    fun navigatedBack() {
        if (navDetails.isNotEmpty()) {
            navDetails.removeAt(navDetails.lastIndex)
            rebuildNavTitle()
        }
    }

    fun navigatedOutOfDetail() {
        navDetails.clear()
        rebuildNavTitle()
    }

    private fun rebuildNavTitle() {
        if (navDetails.isEmpty()) {
            navTitle.postValue(SpannableStringBuilder())
            return
        }
        val spannable = SpannableStringBuilder(navDetails.last().title)
        if (navDetails.size == 1) {
            navTitle.postValue(spannable)
            return
        }
        for (i in navDetails.size - 2 downTo 0) {
            spannable.append("< ${navDetails[i].title}")
        }
        spannable.setSpan(
                textSizeOfPrevious,
                navDetails.last().title.length,
                spannable.count(),
                SPAN_EXCLUSIVE_INCLUSIVE
        )
        navTitle.postValue(spannable)
    }

    companion object {
        private val textSizeOfPrevious = RelativeSizeSpan(0.6f)
    }
}