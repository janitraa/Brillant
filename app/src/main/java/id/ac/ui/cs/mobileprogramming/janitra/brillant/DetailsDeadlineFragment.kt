package id.ac.ui.cs.mobileprogramming.janitra.brillant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.ac.ui.cs.mobileprogramming.janitra.brillant.data.Task
import id.ac.ui.cs.mobileprogramming.janitra.brillant.databinding.FragmentDetailsDeadlinesBinding

class DetailsDeadlineFragment (val data: Task, val position: Int): Fragment() {
    private lateinit var mView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDetailsDeadlinesBinding =
            FragmentDetailsDeadlinesBinding.inflate(inflater, container, false)
        this.mView = binding.getRoot()

        binding.viewTaskName.setText(data.taskName)
        binding.viewDescription.setText(data.description)
        binding.viewTags.setText(data.tags)
        binding.viewStatus.setText(data.status)
        binding.viewDueDate.setText(data.dueDate)
        binding.viewNotif.setText(data.notification)
        return mView
    }
}