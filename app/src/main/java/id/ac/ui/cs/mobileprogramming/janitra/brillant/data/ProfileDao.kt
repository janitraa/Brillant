package id.ac.ui.cs.mobileprogramming.janitra.brillant.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface ProfileDao {

    @Query("SELECT * FROM profile_details")
    fun getAll(): LiveData<List<@JvmSuppressWildcards Profile>>

    @Insert(onConflict = REPLACE)
    fun insert(profile: Profile)

    @Delete
    fun delete(profile: Profile)

    @Update
    fun update(profile: Profile)
}
