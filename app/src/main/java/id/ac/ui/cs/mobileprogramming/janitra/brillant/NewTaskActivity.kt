package id.ac.ui.cs.mobileprogramming.janitra.brillant

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import id.ac.ui.cs.mobileprogramming.janitra.brillant.data.Task
import java.time.Year
import java.util.*

class NewTaskActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    var day = 0
    var month: Int = 0
    var year: Int = 0
    var hour: Int = 0
    var minute: Int = 0
    var myDay = 0
    var myMonth: Int = 0
    var myYear: Int = 0
    var myHour: Int = 0
    var myMinute: Int = 0

    private lateinit var editTaskName: EditText
    private lateinit var editDescription: EditText
    private lateinit var editTags: EditText
    private lateinit var editStatus: EditText
    private lateinit var dueDateBtn: Button

    companion object {
        val EXTRA_TASK_NAME = "id.ac.ui.cs.mobileprogrramming.janitra.brillant.EXTRA_TASK_NAME"
        val EXTRA_DESCRIPTION = "id.ac.ui.cs.mobileprogrramming.janitra.brillant.EXTRA_DESCRIPTION"
        val EXTRA_TAGS = "id.ac.ui.cs.mobileprogrramming.janitra.brillant.EXTRA_TAGS"
        val EXTRA_STATUS = "id.ac.ui.cs.mobileprogrramming.janitra.brillant.EXTRA_STATUS"
        val EXTRA_DUE_DATE = "id.ac.ui.cs.mobileprogrramming.janitra.brillant.EXTRA_DUE_DATE"
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)

        dueDateBtn = findViewById(R.id.btn_due_date)

        dueDateBtn.setOnClickListener{
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)
            val dpd = DatePickerDialog(this, this, year, month, day)
            dpd.show()
        }
        editTaskName = findViewById(R.id.edit_task_name)
        editDescription = findViewById(R.id.edit_description)
        editTags = findViewById(R.id.edit_tags)
        editStatus = findViewById(R.id.edit_status)

        supportActionBar?.setTitle("CANCEL")
        setTitle("New Task")
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int){
        myDay = dayOfMonth
        myYear = year
        myMonth = month
        val cal = Calendar.getInstance()
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)
        val tpd = TimePickerDialog(this, this, hour, minute, DateFormat.is24HourFormat(this))
        tpd.show()
    }

    override fun onTimeSet(view: TimePicker?, hours: Int, minute: Int){
        myHour = hours
        myMinute = minute
    }

    fun newTask() {
        val taskName: String = editTaskName.text.toString()
        val description: String = editDescription.text.toString()
        val tags: String = editTags.text.toString()
        val status: String = editStatus.text.toString()
        val dueDate: String = myDay.toString()

//        if (taskName.trim().isEmpty() || dueDate.trim().isEmpty()) {
//            Toast.makeText(this, "Please insert task name and due date", Toast.LENGTH_SHORT).show()
//            return
//        }

        val data: Intent = Intent()
        data.putExtra(EXTRA_TASK_NAME, taskName)
        data.putExtra(EXTRA_DESCRIPTION, description)
        data.putExtra(EXTRA_TAGS, tags)
        data.putExtra(EXTRA_STATUS, status)
        data.putExtra(EXTRA_DUE_DATE, dueDate)

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