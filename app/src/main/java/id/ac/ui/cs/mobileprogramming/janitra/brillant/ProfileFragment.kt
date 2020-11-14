package id.ac.ui.cs.mobileprogramming.janitra.brillant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.ac.ui.cs.mobileprogramming.janitra.brillant.data.Profile
import id.ac.ui.cs.mobileprogramming.janitra.brillant.databinding.FragmentProfileBinding

class ProfileFragment (val data: Profile, val position: Int): Fragment() {
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
}