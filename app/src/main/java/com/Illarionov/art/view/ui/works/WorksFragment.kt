package com.Illarionov.art.view.ui.works

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.GridLayoutManager
import com.Illarionov.art.ArtistItemDecoration
import com.Illarionov.art.R
import com.Illarionov.art.utils.WorksDiffUtilItemCallback
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_works.*
import navigation.FragmentNavigation
import navigation.NavigationGraph

private const val SPAN_COUNT = 2
private const val ARTIST_ITEM_DECORATION_OFFSET = 8

class WorksFragment : Fragment(), FragmentNavigation {

    private lateinit var worksAdapter: WorksPagedListAdapter
    private lateinit var viewModel: WorksViewModel
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        works_recycler_view.apply {
            layoutManager = GridLayoutManager(context, SPAN_COUNT)
            adapter = worksAdapter
            setHasFixedSize(true)
            addItemDecoration(ArtistItemDecoration(ARTIST_ITEM_DECORATION_OFFSET))
        }
        navigateTo()
        viewModel.worksList.observe(this.viewLifecycleOwner, Observer{worksAdapter.submitList(it)})
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_works, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        worksAdapter = WorksPagedListAdapter(WorksDiffUtilItemCallback())
        bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation_view)
        viewModel = ViewModelProvider(this)[WorksViewModel::class.java]
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
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }

        bottomNavigationView.apply {
            setOnNavigationItemSelectedListener {
                when (it) {
                    this.menu.findItem(R.id.menu_newsFeed) -> {
                        findNavController().navigate(NavigationGraph.Action.to_menu_newsFeed, null, options)
                        it.isChecked = true
                    }

                    this.menu.findItem(R.id.menu_artist) -> {
                        findNavController().navigate(NavigationGraph.Action.to_menu_artist, null, options)
                        it.isChecked = true
                    }
                }
                false
            }
        }
    }
}
