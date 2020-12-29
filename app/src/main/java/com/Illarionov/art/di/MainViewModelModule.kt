package com.Illarionov.art.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.Illarionov.art.factories.ViewModelProviderFactory
import com.Illarionov.art.view.ui.tasks.AddTaskViewModel
import com.Illarionov.art.view.ui.tasks.TaskListViewModel
import com.Illarionov.art.view.ui.works.WorksViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainViewModelModule {

    @Binds
    fun bindsViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(WorksViewModel::class)
    fun bindsWorksViewModel(viewModel: WorksViewModel): ViewModel

    /*@Binds
    @IntoMap
    @ViewModelKey(ArtistViewModel::class)
    fun bindsArtistViewModel(viewModel: ArtistViewModel): ViewModel*/

    @Binds
    @IntoMap
    @ViewModelKey(AddTaskViewModel::class)
    fun bindsAddTaskViewModel(viewModel: AddTaskViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TaskListViewModel::class)
    fun bindsTaskListViewModel(viewModel: TaskListViewModel): ViewModel
}
