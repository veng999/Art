package com.Illarionov.art.view.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.Illarionov.art.R
import com.Illarionov.art.R.anim.*
import com.Illarionov.art.R.id.menu_artist
import com.Illarionov.art.R.id.menu_works
import com.Illarionov.art.R.layout.fragment_news_feed
import com.Illarionov.art.extensions.observe
import com.Illarionov.art.utils.NewsDiffUtilItemCallback
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_news_feed.view.*
import navigation.FragmentNavigation
import navigation.NavigationConstants.Action.to_menu_artist
import navigation.NavigationConstants.Action.to_menu_works

class NewsFragment : Fragment(), FragmentNavigation {

    private lateinit var newsAdapter: ArtistPagedListAdapter
    private lateinit var viewModel: NewsViewModel
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.news_feed_recycler_view.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = newsAdapter
            setHasFixedSize(true)
        }

        navigateTo()
        observe(viewModel.eventsList){
            newsAdapter.submitList(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(fragment_news_feed, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsAdapter = ArtistPagedListAdapter(
            NewsDiffUtilItemCallback() /*object : OnClickItemListener {
            override fun onClickItem(id: String) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }*/)
        bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation_view)
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

    override fun navigateTo() {
        val options = navOptions {
            anim {
                enter = slide_in_right
                exit = slide_out_left
                popEnter = slide_in_left
                popExit = slide_out_right
            }
        }

        bottomNavigationView.apply {
            setOnNavigationItemSelectedListener {
                when (it) {
                    this.menu.findItem(menu_works) -> {
                        findNavController().navigate(to_menu_works, null, options)
                        it.isChecked = true
                    }
                    this.menu.findItem(menu_artist) -> {
                        findNavController().navigate(to_menu_artist, null, options)
                        it.isChecked = true
                    }
                }
                false
            }
        }
    }
}

