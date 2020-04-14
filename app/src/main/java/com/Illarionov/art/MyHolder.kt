package com.Illarionov.art

import android.view.TextureView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView

class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var imageView: ImageView
    val artistInformation: TextView
    val currentExibition: TextureView
    /*val elementOfExibition: PagedList<ImageView>*/

    init {
        imageView = itemView.findViewById(R.id.artistImage)
        artistInformation = itemView.findViewById(R.id.biography)
        currentExibition = itemView.findViewById(R.id.current_exibitions)
        /*elementOfExibition = itemView.findViewById<ImageView>()*/


    }
}