package com.Illarionov.art.holders

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.Illarionov.art.R
import com.Illarionov.art.utils.AnimationHelper
import com.Illarionov.art.databinding.ViewWorkItemBinding
import com.company.myartist.model.Media
import com.company.myartist.model.Work
import com.squareup.picasso.Picasso

private const val DEFAULT_COLOR = "#ffffff"

class WorksHolder(private val binding: ViewWorkItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.apply {
            likeBtn.setOnClickListener { AnimationHelper.rotateView(it) }
            saleBtn.setOnClickListener { AnimationHelper.shakeView(it) }
        }
    }

    fun show(item: Work?) {
        binding.apply {
            val sizeWork =
                itemView.context.resources.getDimension(R.dimen.works_fragment_work_width).toInt()
            val url = item?.media?.makeUrl(Media.MediaRatio.o, Media.MediaSide.x, sizeWork)
            val middleColor = item?.colors?.middle
            val imagePlaceholder = if (middleColor == "") DEFAULT_COLOR  else "#${middleColor}"
            Log.d("imagePlaceholder", imagePlaceholder)
            into(url, imageWork, imagePlaceholder)
            titleWork.text = item?.name ?: "0"
            countWork.text = item?.counters?.likes ?: "0"
        }
    }
}

private fun into(
    url: String?,
    imageView: ImageView?,
    imagePlaceholder: String? = DEFAULT_COLOR
) = Picasso.get().load(url).placeholder(ColorDrawable(Color.parseColor(imagePlaceholder)))
    .into(imageView)

