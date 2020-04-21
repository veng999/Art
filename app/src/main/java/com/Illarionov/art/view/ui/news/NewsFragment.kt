package com.Illarionov.art.view.ui.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.Illarionov.art.ArtistAdapter
import com.Illarionov.art.ArtistDataSourceFactory
import com.Illarionov.art.ArtistRecyclerViewAdapterListener
import com.Illarionov.art.R
import com.Illarionov.art.network.ArtistRemoteDataSource
import com.Illarionov.art.rest_api.ArtistApiService
import com.company.myartist.model.Event
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_news_feed.view.*

class NewsFragment : Fragment() {

    private val TAG = NewsFragment::class.java.simpleName
    private lateinit var artistAdapter: ArtistAdapter
    private lateinit var viewModel: NewsViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_news_feed, container, false)
        root.news_feed_recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = artistAdapter
        }
        return root
    }

    private fun setupViewModel() {
        viewModel = NewsViewModel(
            ArtistDataSourceFactory(ArtistApiService.create()),
            ArtistRemoteDataSource(),
            Schedulers.io()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
    }

    private fun displayEventList(events: List<Event>) {
        artistAdapter.submitList(events)
    }

    private fun displayError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun onStart() {
        super.onStart()
        loadNews()
    }

    private fun loadNews() {
        viewModel.listOfEvents.observe(this.viewLifecycleOwner, Observer{
            when {
                it == null -> displayError("Error fetching event list")
                it.isEmpty() -> Log.d(TAG, "No events to display")
                else -> displayEventList(it)
            }
        })
    }

    private fun setupView() {
        artistAdapter = ArtistAdapter(object: ArtistRecyclerViewAdapterListener {
            override fun onClickEvent(id: String) {
            }
        })
    }
}

