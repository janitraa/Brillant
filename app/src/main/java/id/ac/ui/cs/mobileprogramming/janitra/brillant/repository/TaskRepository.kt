package id.ac.ui.cs.mobileprogramming.janitra.brillant.repository

import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.janitra.brillant.data.TaskDao
import id.ac.ui.cs.mobileprogramming.janitra.brillant.data.Task
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext

class TaskRepository private constructor(
    private val taskDao: TaskDao
) {
    private val allTask: LiveData<List<Task>> = taskDao.getAll()

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    var thread = newSingleThreadContext("taskRepository") as CoroutineDispatcher

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    fun insertTask(task: Task) = GlobalScope.launch(thread) {
        taskDao.insert(task)
    }

    fun getAllTask(): LiveData<List<Task>> {
        return allTask
    }

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    fun deleteTask(task: Task) = GlobalScope.launch(thread) {
        taskDao.delete(task)
    }

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    fun updateTask(task: Task) = GlobalScope.launch(thread) {
        taskDao.update(task)
    }

    companion object {
        @Volatile private var instance: TaskRepository? = null

        fun getInstance(taskDao: TaskDao) =
            instance ?: synchronized(this) {
                instance ?: TaskRepository(taskDao).also { instance = it }
            }
    }
}