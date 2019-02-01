package ru.jjba.jr2.presentation.viewmodel.shared

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Types
import ru.jjba.jr2.App
import ru.jjba.jr2.presentation.viewmodel.util.defaultValue

class NavDetailViewModel : ViewModel() {
    private val jsonAdapter: JsonAdapter<MutableList<NavDetail>> by lazy {
        App.instance.moshi.adapter<MutableList<NavDetail>>(navDetailsType)
    }

    private val title = MutableLiveData<SpannableStringBuilder>()
    private val details = MutableLiveData<MutableList<NavDetail>>().defaultValue(mutableListOf())

    fun getTitleLiveData(): LiveData<SpannableStringBuilder> = title

    fun getDetailsLiveData(): LiveData<MutableList<String>> {
        return Transformations.map(details) {
            it.mapIndexed { i, navDetail ->
                "${i + 1}) ${navDetail.title} ${navDetail.fragmentType}"
            }.toMutableList()
        }
    }

    fun onNavigatedForward(detail: NavDetail) = details.value?.let {
        if (!it.contains(detail)) {
            it.add(detail)
            rebuildNavTitle()
        }
    }

    fun onNavigatedBack(times: Int = 1) = details.value?.run {
        repeat(times) {
            if (this.isNullOrEmpty()) return@repeat
            this.removeAt(this.lastIndex)
        }
        rebuildNavTitle()
    }

    fun onNavigatedOutOfDetail() = details.value?.run {
        this.clear()
        rebuildNavTitle()
    }

    fun onSaveNavDetailsState(): String? = details.value?.run {
        jsonAdapter.toJson(this)
    }

    fun onRestoreNavDetailsState(navDetailsJson: String) {
        jsonAdapter.fromJson(navDetailsJson)?.let {
            details.postValue(it)
            rebuildNavTitle()
        }
    }

    fun onClear() {
        details.postValue(mutableListOf())
    }

    private fun rebuildNavTitle() = details.value?.run {
        if (this.isNullOrEmpty()) {
            title.postValue(SpannableStringBuilder())
            return@run
        }
        val spannable = SpannableStringBuilder(this.last().title)
        if (this.size == 1) {
            title.postValue(spannable)
            return@run
        }
        for (i in this.size - 2 downTo 0) {
            spannable.append("< ${this[i].title}")
        }
        spannable.setSpan(
                RelativeSizeSpan(0.6f),
                this.last().title.length + 1,
                spannable.count(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        title.postValue(spannable)
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
        val fragmentId: Long,
        val fragmentType: String = "слово"
)