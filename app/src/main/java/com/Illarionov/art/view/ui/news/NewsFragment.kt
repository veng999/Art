package com.Illarionov.art.view.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.Illarionov.art.ArtistPagedListAdapter
import com.Illarionov.art.NewsDiffUtilItemCallback
import com.Illarionov.art.OnClickItemListener
import com.Illarionov.art.R
import com.Illarionov.art.network.ArtistRemoteDataSource
import kotlinx.android.synthetic.main.fragment_news_feed.view.*

class NewsFragment : Fragment(){

    private val TAG = NewsFragment::class.java.simpleName
    private lateinit var artistAdapter: ArtistPagedListAdapter
    private lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.news_feed_recycler_view.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = artistAdapter
            setHasFixedSize(true)
        }
        viewModel.eventsList.observe(this.viewLifecycleOwner, Observer{artistAdapter.submitList(it)})
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_news_feed, container, false)

        return root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        artistAdapter = ArtistPagedListAdapter(NewsDiffUtilItemCallback(), object : OnClickItemListener {
            override fun onClickItem(id: String) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
        viewModel = NewsViewModel(ArtistRemoteDataSource())
    }

    /*private fun setProgress(loadState: NetworkState) {
        when (loadState) {
            is Success -> progress.visibility = View.GONE
            is NetworkError -> {
                progress.visibility = View.GONE
                loadState.message?.let { displayError(it) }
            }
            is Loading -> progress.visibility = View.GONE
        }
    }*/
}

