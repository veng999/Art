package com.Illarionov.art.view.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.Illarionov.art.utils.NewsDiffUtilItemCallback
import com.Illarionov.art.R
import kotlinx.android.synthetic.main.fragment_news_feed.view.*

class NewsFragment : Fragment(){

    private val TAG = NewsFragment::class.java.simpleName
    private lateinit var newsAdapter: ArtistPagedListAdapter
    private lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.news_feed_recycler_view.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = newsAdapter
            setHasFixedSize(true)
        }
        viewModel.eventsList.observe(this.viewLifecycleOwner, Observer{newsAdapter.submitList(it)})
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_news_feed, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsAdapter = ArtistPagedListAdapter(
            NewsDiffUtilItemCallback() /*object : OnClickItemListener {
            override fun onClickItem(id: String) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }*/)
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        /*viewModel = NewsViewModel(ArtistRemoteDataSource())*/
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

