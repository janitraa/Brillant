package id.ac.ui.cs.mobileprogramming.janitra.brillant

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.ac.ui.cs.mobileprogramming.janitra.brillant.data.Profile
import id.ac.ui.cs.mobileprogramming.janitra.brillant.databinding.FragmentProfileBinding
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment (val data: Profile, val position: Int): Fragment() {
    val GALLERY_REQUEST_CODE = 1

    val SHARED_PREF_PROFILE_PAGE = "profile-page"
    val SHARED_PREF_PROFILE_PIC = "profile-pic"

    private lateinit var binding: FragmentProfileBinding
    private lateinit var mView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentProfileBinding =
            FragmentProfileBinding.inflate(inflater, container, false)
        this.mView = binding.getRoot()

        binding.viewName.setText(data.firstName)
        binding.viewEmail.setText(data.email)
        binding.viewDob.setText(data.dob)
        binding.viewGoals.setText(data.goals)
        binding.viewDreamJob.setText(data.dreamJob)
        return mView
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                GALLERY_REQUEST_CODE -> {
                    var selectedImage = data?.getData()
                    binding.viewImage.setImageURI(selectedImage)

                    val sharedPref = activity?.getSharedPreferences(SHARED_PREF_PROFILE_PAGE, Context.MODE_PRIVATE) ?: return
                    with(sharedPref.edit()) {
                        putString(SHARED_PREF_PROFILE_PIC, selectedImage.toString())
                        commit()
                    }
                }
                else -> {
                    return
                }
            }
        }
    }
}