package io.github.jr2team.jr2android.presentation.ui.util

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

class ItemOffsetDecoration(
        private val ctx: Context,
        @DimenRes private val resId: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val itemOffset = ctx.resources.getDimensionPixelSize(resId)
        outRect.set(itemOffset, itemOffset, itemOffset, itemOffset)
    }
}