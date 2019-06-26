package io.github.jr2team.jr2android.presentation.viewmodel.shared

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
import io.github.jr2team.jr2android.App
import io.github.jr2team.jr2android.presentation.viewmodel.util.defaultValue

class NavDetailViewModel : ViewModel() {
    private val jsonAdapter: JsonAdapter<MutableList<NavDetail>> by lazy {
        App.instance.moshi.adapter<MutableList<NavDetail>>(navDetailsType)
    }

    private val title = MutableLiveData<SpannableStringBuilder>()
    private val details = MutableLiveData<MutableList<NavDetail>>()
            .defaultValue(mutableListOf())

    init {
        details.observeForever {
            rebuildNavTitle()
        }
    }

    fun getTitleLiveData(): LiveData<SpannableStringBuilder> = title

    fun getDetailsLiveData(): LiveData<MutableList<String>> {
        return Transformations.map(details) {
            it.mapIndexed { i, navDetail ->
                "${i + 1}) ${navDetail.title} ${navDetail.fragmentType}"
            }.toMutableList()
        }
    }

    fun onNavigatedForward(detail: NavDetail) = details.value?.run {
        if (!this.contains(detail)) {
            this.add(detail)
            details.postValue(this)
        }
    }

    fun onNavigatedBack(times: Int = 1) = details.value?.run {
        repeat(times) {
            if (this.isNullOrEmpty()) return@repeat
            this.removeAt(this.lastIndex)
        }
        details.postValue(this)
    }

    fun onNavigatedOutOfDetail() = details.value?.run {
        details.postValue(mutableListOf())
    }

    fun onSaveNavDetailsState(): String? = details.value?.run {
        jsonAdapter.toJson(this)
    }

    fun onRestoreNavDetailsState(navDetailsJson: String) =
            jsonAdapter.fromJson(navDetailsJson)?.let {
                details.postValue(it)
            }

    private fun rebuildNavTitle() = details.value?.run {
        when (this.size) {
            0 -> title.postValue(SpannableStringBuilder())
            1 -> title.postValue(SpannableStringBuilder(this.last().title))
            else -> {
                val spannableTitle = SpannableStringBuilder(this.last().title)
                this.asReversed().subList(1, this.size).map {
                    spannableTitle.append("< ${it.title}")
                }
                spannableTitle.setSpan(
                        RelativeSizeSpan(0.6f),
                        this.last().title.length + 1,
                        spannableTitle.count(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                title.postValue(spannableTitle)
            }
        }
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
        val fragmentType: String
)