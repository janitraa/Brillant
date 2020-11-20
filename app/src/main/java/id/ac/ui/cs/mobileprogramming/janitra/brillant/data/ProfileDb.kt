package id.ac.ui.cs.mobileprogramming.janitra.brillant.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Profile::class], version = 2, exportSchema = false)
abstract class ProfileDb: RoomDatabase() {

    abstract fun profileDao(): ProfileDao

    companion object {

        @Volatile
        private var instance: ProfileDb? = null

        fun getInstance(context: Context) : ProfileDb? {
            if(instance == null) {
                synchronized(ProfileDb::class.java) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            ProfileDb::class.java, "profile_db"
                        )
//                .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return instance
        }
    }
}