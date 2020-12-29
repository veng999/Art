package com.Illarionov.art.view.ui.works

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.Illarionov.art.App
import com.Illarionov.art.ArtistItemDecoration
import com.Illarionov.art.R
import com.Illarionov.art.di.MainComponent
import com.Illarionov.art.extensions.observe
import com.Illarionov.art.utils.WorksDiffUtilItemCallback
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_works.*
import javax.inject.Inject

private const val SPAN_COUNT = 2
private const val ARTIST_ITEM_DECORATION_OFFSET = 8
private const val HAS_FIXED_SIZE = true

class WorksFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: WorksViewModel by viewModels { factory }
    private lateinit var worksAdapter: WorksPagedListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()

        observe(viewModel.worksList) { worksAdapter.submitList(it) }
        observe(viewModel.loading) { srlRefresh.isRefreshing = it }
        observe(viewModel.error) { showSnakeBar(view) }

        setUpSwipeRefresh()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_works, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        worksAdapter = WorksPagedListAdapter(WorksDiffUtilItemCallback())
        MainComponent
            .init(App.getApp().appComponent)
            .injectWorksFragment(this)
    }

    private fun setUpSwipeRefresh(){
        srlRefresh.apply {
            setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.colorAccent))
            setOnRefreshListener { viewModel.onRefresh() }
        }
    }

    private fun showSnakeBar(view: View){
        Snackbar.make(
            view,
            getString(R.string.works_error),
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun setRecyclerView(){
        works_recycler_view.apply {
            layoutManager = GridLayoutManager(context, SPAN_COUNT)
            adapter = worksAdapter
            setHasFixedSize(HAS_FIXED_SIZE)
            addItemDecoration(ArtistItemDecoration(ARTIST_ITEM_DECORATION_OFFSET))
        }
    }
}
