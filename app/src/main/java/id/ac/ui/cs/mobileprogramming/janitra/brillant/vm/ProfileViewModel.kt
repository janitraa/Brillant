package id.ac.ui.cs.mobileprogramming.janitra.brillant.vm

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.*
import id.ac.ui.cs.mobileprogramming.janitra.brillant.data.Profile
import id.ac.ui.cs.mobileprogramming.janitra.brillant.repository.ProfileRepository
import kotlinx.coroutines.*
import java.util.*

class ProfileViewModel (application: Application) : AndroidViewModel(application) {

    val profile = MutableLiveData<Profile>()

    private var profileRepository: ProfileRepository = ProfileRepository(application)

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    var thread = newSingleThreadContext("profileRepository") as CoroutineDispatcher

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    fun insert(profile: Profile) {
        profileRepository.insertProfile(profile)
    }

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    fun update(profile: Profile) {
        profileRepository.updateProfile(profile)
    }

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    fun delete(profile: Profile) {
        profileRepository.deleteProfile(profile)
    }

//    @kotlinx.coroutines.ObsoleteCoroutinesApi
     fun getProfile() {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                var prof: Profile?
                withContext(Dispatchers.IO) {
                    prof = profileRepository.getProfile()
                }
                profile.value = prof
            }
        }
    }

    fun convertToBitmap(stringImage: String): Bitmap {
        val decodeByte = Base64.getDecoder().decode(stringImage)
        return BitmapFactory.decodeByteArray(decodeByte, 0, decodeByte.size)
    }
}