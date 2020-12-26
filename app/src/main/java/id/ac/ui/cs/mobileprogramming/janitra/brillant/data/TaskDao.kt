package id.ac.ui.cs.mobileprogramming.janitra.brillant.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface TaskDao {

    @Query("SELECT * FROM task_details order by dueDate")
    fun getAll(): LiveData<List<@JvmSuppressWildcards Task>>

    @Insert(onConflict = REPLACE)
    fun insert(task: Task)

    @Delete
    fun delete(task: Task)

    @Update
    fun update(task: Task)

    @Query("SELECT * FROM task_details GROUP BY taskName ORDER BY dueDate ASC")
    fun getUnique(): LiveData<List<Task>>
}
