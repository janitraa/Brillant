package id.ac.ui.cs.mobileprogramming.janitra.brillant

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NewTaskActivity : AppCompatActivity() {
    private lateinit var editTaskName: EditText
    private lateinit var editDescription: EditText
    private lateinit var editTags: EditText
    private lateinit var editStatus: EditText
    private lateinit var editDueDate: EditText
    private lateinit var editNotification: EditText

    companion object {
        val EXTRA_TASK_NAME = "id.ac.ui.cs.mobileprogrramming.janitra.brillant.EXTRA_TASK_NAME"
        val EXTRA_DESCRIPTION = "id.ac.ui.cs.mobileprogrramming.janitra.brillant.EXTRA_DESCRIPTION"
        val EXTRA_TAGS = "id.ac.ui.cs.mobileprogrramming.janitra.brillant.EXTRA_TAGS"
        val EXTRA_STATUS = "id.ac.ui.cs.mobileprogrramming.janitra.brillant.EXTRA_STATUS"
        val EXTRA_DUE_DATE = "id.ac.ui.cs.mobileprogrramming.janitra.brillant.EXTRA_DUE_DATE"
        val EXTRA_NOTIFICATION = "id.ac.ui.cs.mobileprogrramming.janitra.brillant.EXTRA_NOTIFICATION"
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)

        editTaskName = findViewById(R.id.edit_task_name)
        editDescription = findViewById(R.id.edit_description)
        editTags = findViewById(R.id.edit_tags)
        editStatus = findViewById(R.id.edit_status)
        editDueDate = findViewById(R.id.edit_due_date)
        editNotification = findViewById(R.id.edit_dream_job)

        supportActionBar?.setTitle("CANCEL")
        setTitle("New Task")
    }

    fun newTask() {
        val taskName: String = editTaskName.text.toString()
        val description: String = editDescription.text.toString()
        val tags: String = editTags.text.toString()
        val status: String = editStatus.text.toString()
        val due_date: String = editDueDate.text.toString()
        val notification: String = editNotification.text.toString()

        if (taskName.trim().isEmpty() || due_date.trim().isEmpty()) {
            Toast.makeText(this, "Please insert task name and due date", Toast.LENGTH_SHORT).show()
            return
        }

        val data: Intent = Intent()
        data.putExtra(EXTRA_TASK_NAME, taskName)
        data.putExtra(EXTRA_DESCRIPTION, description)
        data.putExtra(EXTRA_TAGS, tags)
        data.putExtra(EXTRA_STATUS, status)
        data.putExtra(EXTRA_DUE_DATE, due_date)
        data.putExtra(EXTRA_NOTIFICATION, notification)

        setResult(RESULT_OK, data)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.new_task, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.new_task -> {
                newTask()
                return true
            }

            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
}