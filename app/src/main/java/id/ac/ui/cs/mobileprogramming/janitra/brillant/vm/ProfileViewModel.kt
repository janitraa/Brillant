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
    fun insert(contacts: Profile) {
        profileRepository.insertProfile(contacts)
    }

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    fun update(contacts: Profile) {
        profileRepository.updateProfile(contacts)
    }

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    fun delete(contacts: Profile) {
        profileRepository.deleteProfile(contacts)
    }

    fun getAllContacts(): LiveData<List<Profile>> {
        return allProfile
    }
}