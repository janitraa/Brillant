package id.ac.ui.cs.mobileprogramming.janitra.brillant.ui

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import id.ac.ui.cs.mobileprogramming.janitra.brillant.data.Profile
import id.ac.ui.cs.mobileprogramming.janitra.brillant.databinding.FragmentProfileBinding
import id.ac.ui.cs.mobileprogramming.janitra.brillant.vm.ProfileViewModel

class ProfileFragment: Fragment() {
    private lateinit var profileViewModel: ProfileViewModel

    private lateinit var binding: FragmentProfileBinding
    private lateinit var mView: View

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
            binding.viewName.text = it?.name
            binding.viewEmail.text = it?.email
            binding.viewDreamJob.text = it?.dreamJob
            binding.viewGoals.text = it?.goals
            binding.viewDob.text = it?.dob

            val bitmap: Bitmap = profileViewModel.convertToBitmap(it?.image!!)
            binding.viewImage.setImageBitmap(bitmap)
        })
        return mView
    }

}