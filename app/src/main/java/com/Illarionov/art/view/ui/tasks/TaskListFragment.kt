package com.Illarionov.art.view.ui.tasks

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.Illarionov.art.App
import com.Illarionov.art.databinding.FragmentTaskListBinding
import com.Illarionov.art.di.MainComponent
import com.Illarionov.art.extensions.observe
import com.Illarionov.art.receivers.NotifyBroadcast
import javax.inject.Inject

class TaskListFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private var fragmentTaskListBinding: FragmentTaskListBinding? = null
    private val viewModel: TaskListViewModel by viewModels { factory }
    private var adapter = TaskListAdapter { id, isChecked ->
        viewModel.onCheckedTask(id, isChecked)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentTaskListBinding = FragmentTaskListBinding.inflate(inflater, container, false)
        return fragmentTaskListBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentTaskListBinding?.rvList?.adapter = adapter
        initObservers()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentTaskListBinding = null
    }

    private fun initObservers() {
        observe(viewModel.getTasks()) {
            adapter.items = it
        }
        observe(viewModel.cancelTask) { id ->
            ContextCompat.getSystemService(requireContext(), AlarmManager::class.java)?.cancel(
                PendingIntent.getBroadcast(
                    requireContext(),
                    id,
                    Intent(requireContext(), NotifyBroadcast::class.java),
                    PendingIntent.FLAG_ONE_SHOT
                )
            )
        }
    }

    private fun inject() {
        MainComponent
            .init(App.getApp().appComponent)
            .injectTaskListFragment(this)
    }
}