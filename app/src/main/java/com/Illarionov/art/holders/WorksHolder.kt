package com.Illarionov.art.holders

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.Illarionov.art.OnClickItemListener
import com.Illarionov.art.R
import com.company.myartist.model.Media
import com.Illarionov.art.model.Work
import com.Illarionov.art.model.WorkAndMedia
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_work_item.view.*

class WorksHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    open fun show(item: WorkAndMedia?) {
        item?.let {
            val sizeWork =
                itemView.context.resources.getDimension(R.dimen.works_fragment_work_width).toInt()
            val placeholder =
                ColorDrawable(Color.parseColor("#${item.work.colors?.middle ?: "FFFFFF"}"))

            itemView.imageWork.setImage(
                url = item.media?.makeUrl(Media.MediaRatio.o, Media.MediaSide.x, sizeWork),
                placeholder = placeholder
            )
            itemView.titleWork.text = item.work.name ?: ""
            itemView.countWork.text = item.work.counters?.likes ?: "0"
        }
    }
}

private fun ImageView.setImage(url: String?, placeholder: Drawable) {
    Picasso.get()
        .load(url)
        .placeholder(placeholder)
        .into(this)
}
