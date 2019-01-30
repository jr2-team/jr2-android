package ru.jjba.jr2.presentation.ui.util

import android.text.SpannableStringBuilder
import android.text.Spanned.SPAN_EXCLUSIVE_INCLUSIVE
import android.text.style.RelativeSizeSpan
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Types
import ru.jjba.jr2.App

@JsonClass(generateAdapter = true)
data class NavDetail(
        val title: String,
        val fragmentType: String = "слово"
)

class DetailNavigator {
    private val jsonAdapter: JsonAdapter<MutableList<NavDetail>> by lazy {
        App.instance.moshi.adapter<MutableList<NavDetail>>(navDetailsType)
    }
    private var navDetails = mutableListOf<NavDetail>()
    private val navTitle = MutableLiveData<SpannableStringBuilder>()

    fun observeNavTitle(): LiveData<SpannableStringBuilder> = navTitle
    //fun observeNavDetails(): LiveData<List<String>> =

    // TODO: Возвращать LiveData
    fun getNavigationDetails(): List<String> =
            navDetails.mapIndexed { idx, detail ->
                "${idx + 1}) ${detail.title} (${detail.fragmentType})"
            }

    fun navigatedForward(detail: NavDetail) {
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

    fun saveNavDetailsStateToJson(): String =
            jsonAdapter.toJson(navDetails)

    fun restoreNavDetailFromJson(navDetailsJson: String) {
        jsonAdapter.fromJson(navDetailsJson)?.let {
            navDetails = it
        }
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
                RelativeSizeSpan(0.6f),
                navDetails.last().title.length,
                spannable.count(),
                SPAN_EXCLUSIVE_INCLUSIVE
        )
        navTitle.postValue(spannable)
    }

    companion object {
        private val navDetailsType = Types.newParameterizedType(
                List::class.java,
                NavDetail::class.java
        )
    }
}