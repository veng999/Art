package com.Illarionov.art.holders

import android.view.View
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
            val url = item?.media?.makeUrl(Media.MediaRatio.h, Media.MediaSide.y, Media.sizes[9])
            Picasso.get().load(url).into(imageWork)
            titleWork.text = item?.name
            Picasso.get().load(R.drawable.ic_like).into(likeBtn)
            countWork.text = item?.counters?.likes
            Picasso.get().load(R.drawable.ic_sale)
            Picasso.get().load(R.drawable.ic_info)
        }
    }
}