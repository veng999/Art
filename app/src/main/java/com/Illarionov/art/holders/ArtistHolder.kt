package com.Illarionov.art.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.Illarionov.art.OnClickItemListener
import com.Illarionov.art.databinding.ViewArtistItemBinding
import com.Illarionov.art.utils.DateUtils
import com.company.myartist.model.Event

class ArtistHolder (private val binding: ViewArtistItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun show(item: Event?, listener: OnClickItemListener? = null) {
        binding.apply {
            dateEvent.text = DateUtils.getFormatDate(item)
            nameEvent.text = item?.action
        }
    }
}