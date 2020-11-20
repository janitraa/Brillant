package id.ac.ui.cs.mobileprogramming.janitra.brillant.repository

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
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
}