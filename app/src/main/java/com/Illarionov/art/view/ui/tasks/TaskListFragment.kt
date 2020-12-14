package com.Illarionov.art.view.ui.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.Illarionov.art.R
import com.Illarionov.art.extensions.factory
import com.Illarionov.art.extensions.observe
import kotlinx.android.synthetic.main.fragment_task_list.*

class TaskListFragment : Fragment(){

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
        observe(viewModel.getTasks()){
            adapter.items = it
        }
    }
}