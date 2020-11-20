package id.ac.ui.cs.mobileprogramming.janitra.brillant.util

import android.content.Context
import id.ac.ui.cs.mobileprogramming.janitra.brillant.data.TaskDb
import id.ac.ui.cs.mobileprogramming.janitra.brillant.repository.TaskRepository
import id.ac.ui.cs.mobileprogramming.janitra.brillant.vm.TaskViewModelFactory

object InjectorUtils {
    fun provideTaskViewModelFactory(context: Context): TaskViewModelFactory {

        val taskRepository = TaskRepository.getInstance(TaskDb.getInstance(context).taskDao())
        return TaskViewModelFactory(taskRepository)
    }
}