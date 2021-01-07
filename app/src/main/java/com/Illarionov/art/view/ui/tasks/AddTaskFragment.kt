package com.Illarionov.art.view.ui.tasks

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.AlarmManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionManager
import com.Illarionov.art.App
import com.Illarionov.art.R
import com.Illarionov.art.animations.AnimationHelper
import com.Illarionov.art.di.MainComponent
import com.Illarionov.art.extensions.observe
import com.Illarionov.art.receivers.EXT_ID
import com.Illarionov.art.receivers.EXT_NAME
import com.Illarionov.art.receivers.NotifyBroadcast
import com.Illarionov.art.view.ui.tasks.AddTaskViewModel.Result
import kotlinx.android.synthetic.main.fragment_add_task.*
import java.util.*
import javax.inject.Inject

private const val DATE_FORMAT = "E dd MMM HH:mm"

class AddTaskFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: AddTaskViewModel by viewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_add_task, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        setupDatePicker()
        setObservers()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }

    private fun setupDatePicker() {
        with(datePicker) {
            setCustomLocale(Locale("ru"))
            addOnDateChangedListener { _, date ->
                viewModel.onDateChanged(date)
            }
        }
    }

    private fun initListeners() {
        etName.doOnTextChanged { text, _, _, _ ->
            viewModel.onNameChanged(text)
        }
        switchTime.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onTimeCheckedChange(isChecked)
        }

        addButton.setOnClickListener {
            viewModel.onAddClick()
            val options = AnimationHelper.getNavOptionsWithAnim()
            findNavController().navigate(R.id.menu_tasks_list, null, options)
        }

        cancelButton.setOnClickListener {
            viewModel.onCancelClick()
        }
    }

    private fun setObservers() {
        observe(viewModel.saveEnabled) {
            addButton.isEnabled = it
        }
        observe(viewModel.timeChecked) { isPicked ->
            TransitionManager.beginDelayedTransition(card)
            grTimeEdit.isVisible = isPicked
            notificationAnim.isVisible = !isPicked
        }
        observe(viewModel.dateTime) {
            tvDateTime.text = DateFormat.format(DATE_FORMAT, it)
        }

        observe(viewModel.result) { result ->
            if (result is Result.Add) {
                result.time?.let { time ->
                    ContextCompat.getSystemService(requireContext(), AlarmManager::class.java)
                        ?.let { alarmManager ->
                            val pendingIntent = PendingIntent.getBroadcast(
                                requireContext(),
                                result.id,
                                Intent(
                                    requireContext(),
                                    NotifyBroadcast::class.java
                                )
                                    .putExtra(EXT_NAME, result.name)
                                    .putExtra(EXT_ID, result.id),
                                PendingIntent.FLAG_ONE_SHOT
                            )
                            //Allow to wake up device and send notification
                            AlarmManagerCompat.setExactAndAllowWhileIdle(
                                alarmManager,
                                AlarmManager.RTC_WAKEUP,
                                time,
                                pendingIntent
                            )
                        }
                }
            }
            findNavController().popBackStack()
        }
    }

    private fun inject() {
        MainComponent
            .init(App.getApp().appComponent)
            .injectAddTaskFragment(this)
    }
}