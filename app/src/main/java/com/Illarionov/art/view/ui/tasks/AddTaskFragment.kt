package com.Illarionov.art.view.ui.tasks

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
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
import com.Illarionov.art.ArtImmHelper
import com.Illarionov.art.ArtImmHelperImpl
import com.Illarionov.art.R
import com.Illarionov.art.utils.AnimationHelper
import com.Illarionov.art.databinding.FragmentAddTaskBinding
import com.Illarionov.art.di.MainComponent
import com.Illarionov.art.extensions.observe
import com.Illarionov.art.receivers.EXT_ID
import com.Illarionov.art.receivers.EXT_NAME
import com.Illarionov.art.receivers.NotifyBroadcast
import com.Illarionov.art.view.ui.tasks.AddTaskViewModel.Result
import java.util.*
import javax.inject.Inject

private const val DATE_FORMAT = "E dd MMM HH:mm"

class AddTaskFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private var binding: FragmentAddTaskBinding? = null
    private val viewModel: AddTaskViewModel by viewModels { factory }
    private val artImmHelper: ArtImmHelper by lazy { ArtImmHelperImpl(binding?.etName) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding?.root
    }

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

    override fun onResume() {
        super.onResume()
        artImmHelper.showSoftInput()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setupDatePicker() {
        binding?.let { binding ->
            with(binding.datePicker) {
                setCustomLocale(Locale("ru"))
                addOnDateChangedListener { _, date ->
                    viewModel.onDateChanged(date)
                }
            }
        }
    }

    private fun initListeners() {
        binding?.let { binding ->
            with(binding) {
                etName.setOnEditorActionListener { _, actionId, _ ->
                    return@setOnEditorActionListener when (actionId) {
                        EditorInfo.IME_ACTION_DONE -> {
                            artImmHelper.hideSoftInput()
                            true
                        }
                        else -> false
                    }
                }
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
                cancelButton.setOnClickListener { viewModel.onCancelClick() }
            }
        }
    }

    private fun setObservers() {
        binding?.let { binding ->
            with(binding) {
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
            }
        }

        observe(viewModel.result) { result ->
            if (result is Result.Add) {
                result.time?.let { time ->
                    ContextCompat.getSystemService(requireContext(), AlarmManager::class.java)
                        ?.let { alarmManager ->
                            val pendingIntent = newPendingIntent(result)
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

    private fun newPendingIntent(result: Result.Add): PendingIntent {
        return PendingIntent.getBroadcast(
            context,
            result.id,
            newBroadcastIntent(result),
            PendingIntent.FLAG_ONE_SHOT
        )
    }

    private fun newBroadcastIntent(result: Result.Add): Intent {
        return Intent(context, NotifyBroadcast::class.java)
            .putExtra(EXT_NAME, result.name)
            .putExtra(EXT_ID, result.id)
    }

    private fun inject() {
        MainComponent
            .init(App.getApp().appComponent)
            .injectAddTaskFragment(this)
    }
}