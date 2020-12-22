package com.Illarionov.art.view.ui.works

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.Illarionov.art.ArtistItemDecoration
import com.Illarionov.art.R
import com.Illarionov.art.extensions.factory
import com.Illarionov.art.extensions.observe
import com.Illarionov.art.utils.WorksDiffUtilItemCallback
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_works.*

private const val SPAN_COUNT = 2
private const val ARTIST_ITEM_DECORATION_OFFSET = 8
private const val HAS_FIXED_SIZE = true

class WorksFragment : Fragment() {

    private lateinit var worksAdapter: WorksPagedListAdapter
    private val viewModel: WorksViewModel by viewModels { factory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        works_recycler_view.apply {
            layoutManager = GridLayoutManager(context, SPAN_COUNT)
            adapter = worksAdapter
            setHasFixedSize(HAS_FIXED_SIZE)
            addItemDecoration(ArtistItemDecoration(ARTIST_ITEM_DECORATION_OFFSET))
        }

        observe(viewModel.worksList) { worksAdapter.submitList(it) }

        observe(viewModel.loading) { srlRefresh.isRefreshing = it }

        observe(viewModel.error) {
            Snackbar.make(
                view,
                getString(R.string.works_error),
                Snackbar.LENGTH_SHORT).show()
        }

        srlRefresh.apply {
            setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.colorAccent))
            setOnRefreshListener { viewModel.onRefresh() }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_works, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        worksAdapter = WorksPagedListAdapter(WorksDiffUtilItemCallback())
    }
}
