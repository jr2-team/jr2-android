package io.github.jr2team.jr2android.presentation.ui.util

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

fun ViewGroup.inflate(layoutRes: Int): View =
        LayoutInflater.from(context).inflate(layoutRes, this, false)

var View.isVisible: Boolean
    set(value) {
        this.visibility = if (value) View.VISIBLE else View.GONE
    }
    get() = this.visibility == View.VISIBLE

fun RecyclerView.restoreLayoutState(
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