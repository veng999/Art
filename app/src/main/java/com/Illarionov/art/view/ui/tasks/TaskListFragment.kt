package com.Illarionov.art.view.ui.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.Illarionov.art.App
import com.Illarionov.art.R
import com.Illarionov.art.di.MainComponent
import com.Illarionov.art.extensions.observe
import kotlinx.android.synthetic.main.fragment_task_list.*
import javax.inject.Inject

class TaskListFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: TaskListViewModel by viewModels { factory }
    private val adapter = TaskListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_task_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvList.adapter = adapter
        initObservers()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }

    private fun initObservers(){
        observe(viewModel.getTasks()) {
            adapter.items = it
        }
    }

    private fun inject(){
        MainComponent
            .init(App.getApp().appComponent)
            .injectTaskListFragment(this)
    }
}