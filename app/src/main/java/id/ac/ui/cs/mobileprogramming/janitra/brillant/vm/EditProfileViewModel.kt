package id.ac.ui.cs.mobileprogramming.janitra.brillant.vm

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import id.ac.ui.cs.mobileprogramming.janitra.brillant.data.Profile
import id.ac.ui.cs.mobileprogramming.janitra.brillant.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.util.*

class EditProfileViewModel (application: Application) : AndroidViewModel(application) {
    private lateinit var profile: Profile
    private val profileRepository = ProfileRepository(application)
    val loaded = MutableLiveData<Boolean>()
    val prof = MutableLiveData<List<Profile>>()

    fun insertProfile(name: String, email: String, dob: String, goals: String, dreamJob: String, image: Bitmap){
        GlobalScope.launch(Dispatchers.Main){
            var profiles: List<Profile>?
            loaded.value = false
            withContext(Dispatchers.IO) {
                val imageString = convertBitmap(image)
                profile = Profile(name = name, email = email, dob = dob, goals = goals, dreamJob = dreamJob, image = imageString)
                profileRepository.insertProfile(profile)
                profiles = profileRepository.getAllProfile()
            }
            prof.value = profiles
            loaded.value = true
        }
    }

    private fun convertBitmap(bitmap: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos)
        return Base64.getEncoder().encodeToString(baos.toByteArray())
    }
}