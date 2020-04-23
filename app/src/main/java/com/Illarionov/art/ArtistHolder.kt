package com.Illarionov.art

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.Illarionov.art.utils.DateUtils
import com.company.myartist.model.Event
import kotlinx.android.synthetic.main.view_artist_item.view.*

class ArtistHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun show(item: Event?, listener: OnClickItemListener? = null) {
        itemView.apply {
            dateEvent.text = DateUtils.getFormatDate(item)
            nameEvent.text = item?.action
            setOnClickListener{listener?.onClickItem(item?.user_id ?: "")}
        }
    }
}