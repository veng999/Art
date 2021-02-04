package com.Illarionov.art

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ArtistItemDecoration (private val offset: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.set(offset, offset, offset, offset)
    }

}
