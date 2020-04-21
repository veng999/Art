package com.Illarionov.art

import android.os.Build
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.company.myartist.model.Event
import kotlinx.android.synthetic.main.view_artist_item.view.*
import java.util.*

class ArtistHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun show(item: Event?, listener: OnClickItemListener? = null) {
        itemView.apply {
            dateEvent.text = item?.getFormatDate(Date().time) // todo логику трансформаций лучше выносить либо в модель либо в утилитный класс
            nameEvent.text = item?.action
            setOnClickListener{listener?.onClickItem(item?.user_id ?: "")}
        }
    }
}