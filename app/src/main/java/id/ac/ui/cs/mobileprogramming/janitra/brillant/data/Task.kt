package id.ac.ui.cs.mobileprogramming.janitra.brillant.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_details")
data class Task (
    @ColumnInfo(name = "taskName") var taskName: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "tags") var tags: String,
    @ColumnInfo(name = "status") var status: String,
    @ColumnInfo(name = "dueDate") var dueDate: String,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int = 0)