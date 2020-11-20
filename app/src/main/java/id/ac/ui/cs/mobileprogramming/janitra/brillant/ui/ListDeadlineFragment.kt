package id.ac.ui.cs.mobileprogramming.janitra.brillant.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.ViewModelProviders
import id.ac.ui.cs.mobileprogramming.janitra.brillant.R
import id.ac.ui.cs.mobileprogramming.janitra.brillant.data.Task
import id.ac.ui.cs.mobileprogramming.janitra.brillant.databinding.FragmentListDeadlinesBinding
import id.ac.ui.cs.mobileprogramming.janitra.brillant.vm.TaskViewModel
import id.ac.ui.cs.mobileprogramming.janitra.brillant.util.InjectorUtils

class ListDeadlineFragment: Fragment() {
    private lateinit var recyclerView: RecyclerView
    val ADD_DEADLINE_REQUEST = 1
    private lateinit var mView: View
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentListDeadlinesBinding = FragmentListDeadlinesBinding.inflate(inflater, container, false)
        this.mView = binding.root
        this.recyclerView = mView.findViewById(R.id.recycler_view)

        val buttonAddTask = mView.findViewById(R.id.button_add) as ImageButton
        buttonAddTask.setOnClickListener {
            val intent = Intent(mView.context, NewTaskActivity::class.java)
            startActivityForResult(intent, ADD_DEADLINE_REQUEST)
        }

        showRecyclerList()
        return mView
    }

    fun goToSelectedTask(data: Task, position: Int) {
        val fragment = DetailsDeadlineFragment(
            data,
            position
        )
        val fragmentTransaction: FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragment_home, fragment, fragment.toString())
        fragmentTransaction?.addToBackStack(fragment.toString())
        fragmentTransaction?.commit()
    }

    private fun showRecyclerList() {
        recyclerView.setLayoutManager(LinearLayoutManager(mView.context))
        recyclerView.setHasFixedSize(true)

        val adapter = TaskAdapter()
        recyclerView.setAdapter(adapter)

        val factory = InjectorUtils.provideTaskViewModelFactory(mView.context)

        taskViewModel = ViewModelProviders.of(this, factory)
            .get(TaskViewModel::class.java)
        taskViewModel.getAllTask().observe(this, Observer { task ->
            adapter.setTask(task)
        })

        adapter.setOnItemClickCallback(object:
            TaskAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Task, position: Int) {
                goToSelectedTask(data, position)
            }
        })
    }

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_DEADLINE_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                val taskName: String = data.getStringExtra(NewTaskActivity.EXTRA_TASK_NAME)?: ""
                val description: String = data.getStringExtra(NewTaskActivity.EXTRA_DESCRIPTION)?: ""
                val tags: String = data.getStringExtra(NewTaskActivity.EXTRA_TAGS)?: ""
                val status: String = data.getStringExtra(NewTaskActivity.EXTRA_STATUS)?: ""
                val dueDate: String = data.getStringExtra(NewTaskActivity.EXTRA_DUE_DATE)?: ""

                val task = Task(taskName, description, tags, status, dueDate)

                taskViewModel.insert(task)

                Toast.makeText(mView.context, "Task Saved", Toast.LENGTH_SHORT).show()
            }
        }
    }
}