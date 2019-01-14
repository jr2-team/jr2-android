package ru.jjba.jr2.utils

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

fun ViewGroup.inflate(layoutRes: Int): View =
        LayoutInflater.from(context).inflate(layoutRes, this, false)

var View.isVisible: Boolean
    set(value) {
        this.visibility = if (value) View.VISIBLE else View.GONE
    }
    get() = this.visibility == View.VISIBLE

fun <T : ViewModel> T.createFactory(): ViewModelProvider.Factory {
    val viewModel = this
    return object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T = viewModel as T
    }
}

fun RecyclerView.restoreState(
        layoutState: Parcelable?,
        decorationsToRemove: Int = 1
) = with(this) {
    if (layoutState != null) {
        while (itemDecorationCount > decorationsToRemove) {
            removeItemDecorationAt(0)
        }
        layoutManager?.onRestoreInstanceState(layoutState)
    }
}