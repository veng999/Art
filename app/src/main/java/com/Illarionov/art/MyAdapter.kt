package com.Illarionov.art

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.Illarionov.art.model.artistsInfo.ArtistInfo
import com.Illarionov.art.stringUtils.StringUtil

class MyAdapter(val context: Context, val artistsInfo: Array<ArtistInfo>) : RecyclerView.Adapter<MyHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_news_feed, parent, false)
        return MyHolder(v)
    }

    override fun getItemCount(): Int {
        return artistsInfo.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val itemOfArtistInfo = artistsInfo[position]
        val itemOfArtists = itemOfArtistInfo.artists[position]
        val itemOfMedia = itemOfArtistInfo.media[position]

        val biography = StringUtil().fromHtml(itemOfArtists.biography)

    }

}