package id.ac.ui.cs.mobileprogramming.janitra.brillant.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy

@Dao
interface ProfileDao {

    @Query("SELECT * FROM profile_details WHERE id=1")
    suspend fun getProfile(): Profile

    @Query("SELECT * FROM profile_details")
    fun getAllProfile(): List<Profile>

    @Query("SELECT * FROM profile_details WHERE firstName=:firstName")
    abstract fun getUserByName(firstName: String): Profile

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(profile: Profile)

    @Delete
    fun delete(profile: Profile)

    @Update
    fun update(profile: Profile)
}
