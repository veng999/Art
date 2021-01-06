package com.Illarionov.art.holders

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.Illarionov.art.R
import com.Illarionov.art.animations.AnimationHelper
import com.company.myartist.model.Media
import com.company.myartist.model.Work
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_work_item.view.*

class WorksHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.apply {
            likeBtn.setOnClickListener { AnimationHelper.rotateView(it) }
            saleBtn.setOnClickListener { AnimationHelper.shakeView(it) }
        }
    }

    fun show(item: Work?) {
        itemView.apply {
            val sizeWork =
                itemView.context.resources.getDimension(R.dimen.works_fragment_work_width).toInt()
            val url = item?.media?.makeUrl(Media.MediaRatio.o, Media.MediaSide.x, sizeWork)
            val imagePlaceholder = "#${item?.colors?.middle}"
            into(url, imageWork, imagePlaceholder)
            titleWork.text = item?.name ?: "0"
            countWork.text = item?.counters?.likes ?: "0"
        }
    }
}

private fun into(
    url: String?,
    imageView: ImageView?,
    imagePlaceholder: String? = "#FFFFFF"
) = Picasso.get().load(url).placeholder(ColorDrawable(Color.parseColor(imagePlaceholder))).into(imageView)

