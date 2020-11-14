package id.ac.ui.cs.mobileprogramming.janitra.brillant.util

import android.content.Context
import id.ac.ui.cs.mobileprogramming.janitra.brillant.data.ProfileDb
import id.ac.ui.cs.mobileprogramming.janitra.brillant.repository.ProfileRepository
import id.ac.ui.cs.mobileprogramming.janitra.brillant.vm.ProfileViewModelFactory

object InjectorUtils {
    fun provideProfileViewModelFactory(context: Context): ProfileViewModelFactory {

        val profileRepository = ProfileRepository.getInstance(ProfileDb.getInstance(context).profileDao())
        return ProfileViewModelFactory(profileRepository)
    }
}