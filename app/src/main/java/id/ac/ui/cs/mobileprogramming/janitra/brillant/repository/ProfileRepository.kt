package id.ac.ui.cs.mobileprogramming.janitra.brillant.repository

import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.janitra.brillant.data.ProfileDao
import id.ac.ui.cs.mobileprogramming.janitra.brillant.data.Profile
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext

class ProfileRepository private constructor(
    private val profileDao: ProfileDao
) {
    private val allProfile: LiveData<List<Profile>> = profileDao.getAll()

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    var thread = newSingleThreadContext("profileRepository") as CoroutineDispatcher

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    fun insertProfile(profile: Profile) = GlobalScope.launch(thread) {
        profileDao.insert(profile)
    }

    fun getAllProfile(): LiveData<List<Profile>> {
        return allProfile
    }

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    fun deleteProfile(profile: Profile) = GlobalScope.launch(thread) {
        profileDao.delete(profile)
    }

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    fun updateProfile(profile: Profile) = GlobalScope.launch(thread) {
        profileDao.update(profile)
    }

    companion object {
        @Volatile private var instance: ProfileRepository? = null

        fun getInstance(profileDao: ProfileDao) =
            instance ?: synchronized(this) {
                instance ?: ProfileRepository(profileDao).also { instance = it }
            }
    }
}