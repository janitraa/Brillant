package id.ac.ui.cs.mobileprogramming.janitra.brillant.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 2, exportSchema = false)
abstract class TaskDb: RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {

        @Volatile
        private var instance: TaskDb? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                context.getApplicationContext(),
                TaskDb::class.java,
                "task_db"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}