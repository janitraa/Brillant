package id.ac.ui.cs.mobileprogramming.janitra.brillant.vm

import android.app.Application
import android.graphics.Bitmap
import android.util.Log
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

    fun insertProfile(firstName: String, lastName: String, email: String, dob: String, goals: String, dreamJob: String, image: Bitmap){
        GlobalScope.launch(Dispatchers.Main){
            loaded.value = false
            withContext(Dispatchers.IO) {
                val imageString = convertBitmap(image)
                profile = Profile(firstName = firstName, lastName = lastName, email = email, dob = dob, goals = goals, dreamJob = dreamJob, image = imageString)
                profileRepository.insertProfile(profile)

//                prof.value = profile
//                loaded.value = true
            }
            prof.value = profileRepository.getAllProfile()
            loaded.value = true
        }
    }

    private fun convertBitmap(bitmap: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos)
        return Base64.getEncoder().encodeToString(baos.toByteArray())
    }
}