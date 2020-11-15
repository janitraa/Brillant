package id.ac.ui.cs.mobileprogramming.janitra.brillant.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.janitra.brillant.repository.TaskRepository

class TaskViewModelFactory (private val taskRepository: TaskRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        return TaskViewModel(taskRepository) as T
    }
}