package com.Illarionov.art.holders

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.Illarionov.art.OnClickItemListener
import com.Illarionov.art.R
import com.company.myartist.model.Media
import com.company.myartist.model.Work
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_work_item.view.*

class WorksHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun show(item: Work?, listener: OnClickItemListener? = null) {
        itemView.apply {
            val sizeWork = itemView.context.resources.getDimension(R.dimen.works_fragment_work_width).toInt()
            val url = item?.media?.makeUrl(Media.MediaRatio.o, Media.MediaSide.x, sizeWork)
            val imagePlaceholder = "#" + item?.colors?.middle

            imageWork.into(url, imageWork, imagePlaceholder)
            val title = item?.name ?: "0"
            titleWork.text = title

            val likes = item?.counters?.likes ?: "0"
            countWork.text = likes
        }
    }
}

private fun ImageView.into(url: String?, imageView: ImageView?, imagePlaceholder: String? = "#FFFFFF") {
    Picasso.get().load(url).placeholder(ColorDrawable(Color.parseColor(imagePlaceholder))).into(imageView)
}
