package id.ac.ui.cs.mobileprogramming.janitra.brillant.repository

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.janitra.brillant.data.ProfileDao
import id.ac.ui.cs.mobileprogramming.janitra.brillant.data.Profile
import id.ac.ui.cs.mobileprogramming.janitra.brillant.data.ProfileDb
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ProfileRepository (application: Application) : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var profileDao: ProfileDao?

    init {
        val db = ProfileDb.getInstance(application)
        profileDao = db?.profileDao()
    }

//    private val allProfile: List<Profile> =

//    @kotlinx.coroutines.ObsoleteCoroutinesApi
//    var thread = newSingleThreadContext("profileRepository") as CoroutineDispatcher

//    @kotlinx.coroutines.ObsoleteCoroutinesApi
//    fun insertProfile(profile: Profile) = GlobalScope.launch(thread) {
//        profileDao?.insert(profile)
//    }

    fun insertProfile(profile: Profile) {
        profileDao?.insert(profile)
        Log.d(TAG,profileDao?.getProfile().toString())

    }

    fun getProfile(): Profile? {
        return profileDao?.getProfile()
    }

    fun getAllProfile(): List<Profile> {
        return profileDao!!.getAllProfile()
    }

//    suspend fun getProfileByName(): Profile? {
//        return profileDao.getProfile()
//    }

//    private suspend fun insertUserBg(profile: Profile) {
//        withContext(Dispatchers.IO) {
//            profileDao?.insert(profile)
//        }
//    }
//
//    @kotlinx.coroutines.ObsoleteCoroutinesApi
//    fun deleteProfile(profile: Profile) = GlobalScope.launch(thread) {
//        profileDao?.delete(profile)
//    }
//
//    @kotlinx.coroutines.ObsoleteCoroutinesApi
//    fun updateProfile(profile: Profile) = GlobalScope.launch(thread) {
//        profileDao?.update(profile)
//    }

//    companion object {
//        @Volatile private var instance: ProfileRepository? = null
//
//        fun getInstance(profileDao: ProfileDao) =
//            instance ?: synchronized(this) {
//                instance ?: ProfileRepository(ProfileDb).also { instance = it }
//            }
//    }
}