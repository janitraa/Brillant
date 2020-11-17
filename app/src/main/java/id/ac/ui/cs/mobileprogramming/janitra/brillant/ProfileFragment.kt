package id.ac.ui.cs.mobileprogramming.janitra.brillant

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import id.ac.ui.cs.mobileprogramming.janitra.brillant.data.Profile
import id.ac.ui.cs.mobileprogramming.janitra.brillant.databinding.FragmentProfileBinding

class ProfileFragment: Fragment() {
    val GALLERY_REQUEST_CODE = 1
    val ADD_DEADLINE_REQUEST = 1
    val SHARED_PREF_PROFILE_PAGE = "profile-page"
    val SHARED_PREF_PROFILE_PIC = "profile-pic"

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

        val editBtn = mView.findViewById(R.id.edit_profile_btn) as Button
        editBtn.setOnClickListener {
            val intent = Intent(mView.context, EditProfileActivity::class.java)
            startActivityForResult(intent, ADD_DEADLINE_REQUEST)
        }
//
//        binding.viewName.setText(this.data.firstName)
//        binding.viewEmail.setText(this.data.email)
//        binding.viewDob.setText(this.data.dob)
//        binding.viewGoals.setText(this.data.goals)
//        binding.viewDreamJob.setText(this.data.dreamJob)

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