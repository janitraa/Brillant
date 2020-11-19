package id.ac.ui.cs.mobileprogramming.janitra.brillant

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import id.ac.ui.cs.mobileprogramming.janitra.brillant.data.Profile
import id.ac.ui.cs.mobileprogramming.janitra.brillant.databinding.FragmentProfileBinding
import id.ac.ui.cs.mobileprogramming.janitra.brillant.vm.ProfileViewModel

class ProfileFragment: Fragment() {
    val GALLERY_REQUEST_CODE = 1
    val ADD_DEADLINE_REQUEST = 1
    val SHARED_PREF_PROFILE_PAGE = "profile-page"
    val SHARED_PREF_PROFILE_PIC = "profile-pic"
    private lateinit var profileViewModel: ProfileViewModel


    private lateinit var binding: FragmentProfileBinding
//    val binding = FragmentProfileBinding()
    private lateinit var mView: View
    private lateinit var data: Profile

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentProfileBinding.inflate(inflater, container, false)
        this.mView = binding.getRoot()

        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        profileViewModel.getProfile()

        profileViewModel.profile.observe(viewLifecycleOwner, Observer<Profile> {
            binding.viewName.text = it?.firstName
            val bitmap: Bitmap = profileViewModel.convertToBitmap(it?.image!!)
            binding.viewImage.setImageBitmap(bitmap)
        })

        val editBtn = mView.findViewById(R.id.edit_profile_btn) as Button
        editBtn.setOnClickListener {
            val intent = Intent(mView.context, EditProfileActivity::class.java)
            startActivityForResult(intent, ADD_DEADLINE_REQUEST)
        }
//
//        binding.viewName.setText(data.firstName)
//        binding.viewEmail.setText(data.email)
//        binding.viewDob.setText(data.dob)
//        binding.viewGoals.setText(data.goals)
//        binding.viewDreamJob.setText(data.dreamJob)

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