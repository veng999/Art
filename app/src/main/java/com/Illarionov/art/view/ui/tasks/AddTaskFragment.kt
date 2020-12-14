package com.Illarionov.art.view.ui.tasks

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.AlarmManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionManager
import com.Illarionov.art.R
import com.Illarionov.art.extensions.factory
import com.Illarionov.art.extensions.observe
import com.Illarionov.art.receivers.EXT_ID
import com.Illarionov.art.receivers.EXT_NAME
import com.Illarionov.art.receivers.NotifyBroadcast
import com.Illarionov.art.view.ui.tasks.AddTaskViewModel.Result
import kotlinx.android.synthetic.main.fragment_add_task.*
import navigation.NavigationConstants
import java.util.*

private const val DATE_FORMAT = "E dd MMM HH:mm"

class AddTaskFragment : Fragment(){

    private val viewModel: AddTaskViewModel by viewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_add_task, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etName.doOnTextChanged { text, _, _, _ ->
            viewModel.onNameChanged(text)
        }
        switchTime.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onTimeCheckedChange(isChecked)
        }

        addButton.setOnClickListener {
            viewModel.onAddClick()
            findNavController().navigate(NavigationConstants.Action.to_task_list_fragment, null, null)
        }

        setupDatePicker()
        cancelButton.setOnClickListener {
            viewModel.onCancelClick()
        }
        setObservers()
    }

    private fun setupDatePicker(){
        with(datePicker){
            setCustomLocale(Locale("ru"))
            addOnDateChangedListener { _, date ->
                viewModel.onDateChanged(date)
            }
        }
    }


    private fun setObservers(){
        observe(viewModel.saveEnabled){
            addButton.isEnabled = it
        }
        observe(viewModel.timeChecked){
            TransitionManager.beginDelayedTransition(card)
            grTimeEdit.isVisible = it
        }
        observe(viewModel.dateTime){
            tvDateTime.text = android.text.format.DateFormat.format(DATE_FORMAT, it)
        }

        observe(viewModel.result){ result ->
            if(result is Result.Add){
                result.time?.let { time ->
                    ContextCompat.getSystemService(requireContext(), AlarmManager::class.java)?.let { alarmManager ->
                        val pendingIntent = PendingIntent.getBroadcast(
                            requireContext(),
                            result.id,
                            Intent(requireContext(),
                                NotifyBroadcast::class.java)
                                .putExtra(EXT_NAME, result.name)
                                .putExtra(EXT_ID, result.id),
                            PendingIntent.FLAG_ONE_SHOT)
                        //Allow to wake up device and send notification
                        AlarmManagerCompat.setExactAndAllowWhileIdle(
                            alarmManager,
                            AlarmManager.RTC_WAKEUP,
                            time,
                            pendingIntent)
                    }
                }
            }
            findNavController().popBackStack()
        }
    }
}