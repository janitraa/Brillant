package id.ac.ui.cs.mobileprogramming.janitra.brillant.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.janitra.brillant.data.Task
import id.ac.ui.cs.mobileprogramming.janitra.brillant.repository.TaskRepository

class TaskViewModel (
    private val taskRepository: TaskRepository
): ViewModel() {
    private val allTask: LiveData<List<Task>> = taskRepository.getAllTask()

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    fun insert(task: Task) {
        taskRepository.insertTask(task)
    }

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    fun update(task: Task) {
        taskRepository.updateTask(task)
    }

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    fun delete(task: Task) {
        taskRepository.deleteTask(task)
    }

    fun getAllTask(): LiveData<List<Task>> {
        return allTask
    }
}