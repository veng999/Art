package com.Illarionov.art

import android.os.Build
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.company.myartist.model.Event

class ArtistHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    val date: TextView = itemView.findViewById(R.id.date_of_news)
    val action: TextView = itemView.findViewById(R.id.action)

    @RequiresApi(Build.VERSION_CODES.O)
    fun show(item: Event?, listener: OnClickItemListener? = null) {
        itemView.apply {
            val formattedTime = fromUnixToDate(item?.date!!.toLong())
            date.text = formattedTime
            action.text = item.action

            itemView.setOnClickListener{listener?.onClickItem(item.user_id ?: "")}
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun fromUnixToDate(unixTime: Long) : String {
        return java.time.format
            .DateTimeFormatter
            .ISO_INSTANT
            .format(java.time.Instant.ofEpochSecond(unixTime))
    }
}