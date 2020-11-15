package id.ac.ui.cs.mobileprogramming.janitra.brillant.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.janitra.brillant.data.Profile
import id.ac.ui.cs.mobileprogramming.janitra.brillant.repository.ProfileRepository

class ProfileViewModel (
    private val profileRepository: ProfileRepository
): ViewModel() {
    private val allProfile: LiveData<List<Profile>> = profileRepository.getAllProfile()

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

    fun getAllProfile(): LiveData<List<Profile>> {
        return allProfile
    }
}