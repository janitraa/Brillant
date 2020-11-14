package id.ac.ui.cs.mobileprogramming.janitra.brillant.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface ProfileDao {

    @Query("SELECT * FROM profile_details")
    fun getAll(): LiveData<List<@JvmSuppressWildcards Profile>>

    @Insert(onConflict = REPLACE)
    fun insert(contacts: Profile)

    @Delete
    fun delete(contacts: Profile)

    @Update
    fun update(contacts: Profile)
}
