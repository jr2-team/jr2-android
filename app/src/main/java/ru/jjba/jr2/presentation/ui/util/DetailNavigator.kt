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

class DetailNavigator {
    private val jsonAdapter: JsonAdapter<MutableList<NavDetail>> by lazy {
        App.instance.moshi.adapter<MutableList<NavDetail>>(navDetailsType)
    }
    private var navDetails = mutableListOf<NavDetail>()
    private val navTitle = MutableLiveData<SpannableStringBuilder>()

    fun getNavTitleLive(): LiveData<SpannableStringBuilder?> = navTitle

    fun observeNavDetails(): LiveData<List<String>> {
        val navList = navDetails.mapIndexed { idx, detail ->
            "${idx + 1}) ${detail.title} (${detail.fragmentType})"
        }
        return MutableLiveData<List<String>>().also {
            it.postValue(navList)
        }
    }

    fun navigatedForward(detail: NavDetail) {
        // TODO: Переделать
        if (!navDetails.contains(detail)) {
            navDetails.add(detail)
            updateNavTitle()
        }
    }

    fun navigatedBack(times: Int = 1) {
        var i = 0
        while (navDetails.isNotEmpty() && i in 0..times) {
            navDetails.removeAt(navDetails.lastIndex)
            i++
        }
        updateNavTitle()
    }

    fun navigatedOutOfDetail() {
        navDetails.clear()
        updateNavTitle()
    }

    fun saveNavDetailsStateToJson(): String =
            jsonAdapter.toJson(navDetails)

    fun restoreNavDetailFromJson(navDetailsJson: String) {
        jsonAdapter.fromJson(navDetailsJson)?.let {
            navDetails = it
        }
        updateNavTitle()
    }

    private fun updateNavTitle() {
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

@JsonClass(generateAdapter = true)
data class NavDetail(
        val title: String,
        val fragmentType: String = "слово"
)