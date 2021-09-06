package com.Illarionov.art.di

import com.Illarionov.art.view.ui.artist.ArtistFragment
import com.Illarionov.art.view.ui.tasks.AddTaskFragment
import com.Illarionov.art.view.ui.tasks.TaskListFragment
import com.Illarionov.art.view.ui.works.WorksFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [
        MainViewModelModule::class,
        StorageModule::class,
        NetworkModule::class]
)
interface MainComponent {
    fun injectWorksFragment(fragment: WorksFragment)
    fun injectArtistFragment(fragment: ArtistFragment)
    fun injectAddTaskFragment(fragment: AddTaskFragment)
    fun injectTaskListFragment(fragment: TaskListFragment)

    @Component.Builder
    interface Builder {
        fun applicationComponent(applicationComponent: ApplicationComponent): Builder

        fun build(): MainComponent
    }

    companion object {
        fun init(applicationComponent: ApplicationComponent): MainComponent {
            return DaggerMainComponent
                .builder()
                .applicationComponent(applicationComponent)
                .build()
        }
    }
}