package com.Illarionov.art.view.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.Illarionov.art.databinding.FragmentNewsFeedBinding
import com.Illarionov.art.utils.NewsDiffUtilItemCallback


class NewsFragment : Fragment() {

    private lateinit var newsAdapter: ArtistPagedListAdapter
    private var binding: FragmentNewsFeedBinding? = null
    /*private lateinit var viewModel: NewsViewModel*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.newsFeedRecyclerView?.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = newsAdapter
            setHasFixedSize(true)
        }

        /*observe(viewModel.eventsList){
            newsAdapter.submitList(it)
        }*/
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsFeedBinding.inflate(inflater, container, false)
        return binding?.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsAdapter = ArtistPagedListAdapter(
            NewsDiffUtilItemCallback() /*object : OnClickItemListener {
            override fun onClickItem(id: String) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }*/)
        /*viewModel = ViewModelProvider(this)[NewsViewModel::class.java]*/
        /*viewModel = NewsViewModel(ArtistRemoteDataSource())*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
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

