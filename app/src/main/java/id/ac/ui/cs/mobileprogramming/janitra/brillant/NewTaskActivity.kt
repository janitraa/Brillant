package id.ac.ui.cs.mobileprogramming.janitra.brillant

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.format.DateFormat
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import androidx.core.app.NotificationCompat
import id.ac.ui.cs.mobileprogramming.janitra.brillant.broadcastreceiver.NotificationPublisher
import java.text.SimpleDateFormat


class NewTaskActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    var day = 0
    var month: Int = 0
    var year: Int = 0
    var hour: Int = 0
    var minute: Int = 0
    var mDay = 0
    var mMonth: Int = 0
    var mYear: Int = 0
    var mHour: Int = 0
    var mMinute: Int = 0
    private var txtDate: String = ""
    private var txtTime: String = ""

    private val cal: Calendar = Calendar.getInstance()

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
        val NOTIFICATION_CHANNEL_ID = "10001"
        val default_notification_channel_id = "default"
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

    private fun scheduleNotification(notification: Notification, delay: Long) {
        val notificationIntent = Intent(this, NotificationPublisher::class.java)
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1)
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification)
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC_WAKEUP, delay, pendingIntent)
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
//            cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent)
    }

    private fun getNotification(content: String): Notification {
//        val resultIntent = Intent(this, MainActivity::class.java)
//        val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(this).run {
//            addNextIntentWithParentStack(resultIntent)
//            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
//        }
        val builder = NotificationCompat.Builder(this, default_notification_channel_id)
        builder.setContentTitle("Due Date")
        builder.setContentText(content)
        builder.setSmallIcon(R.drawable.ic_launcher_foreground)
        builder.setAutoCancel(true)
        builder.setChannelId(NOTIFICATION_CHANNEL_ID)
        return builder.build()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int){
//        mDay = dayOfMonth
//        mYear = year
//        mMonth = month
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        cal.set(Calendar.MONTH, month)
        cal.set(Calendar.YEAR, year)
        val cal = Calendar.getInstance()
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)

        dueDateBtn.text = SpannableStringBuilder(txtDate)

        txtDate = "$mDay/$mMonth/$mYear"

        dueDateBtn.text = txtDate

        val tpd = TimePickerDialog(this, this, hour, minute, DateFormat.is24HourFormat(this))
        tpd.show()
    }

    override fun onTimeSet(view: TimePicker?, hours: Int, minute: Int){
        cal.set(Calendar.HOUR_OF_DAY, hours)
        cal.set(Calendar.MINUTE, minute)
        cal.set(Calendar.SECOND, 0)
//        mMinute = minute
//        txtTime = "$mHour:$mMinute"
//        dueDateBtn.text = txtDate + txtTime
        updateLabel()

    }

    private fun updateLabel() {
        val format = "dd/MM/yy hh:mm a" //In which you need put here
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        txtDate = sdf.format(cal.time)
        dueDateBtn.setText(txtDate)
//        scheduleNotification(getNotification(btnDate.getText().toString()), date.getTime())
    }

    fun newTask() {
        val taskName: String = editTaskName.text.toString()
        val description: String = editDescription.text.toString()
        val tags: String = editTags.text.toString()
        val status: String = editStatus.text.toString()
        val dueDate: String = txtDate

        if (taskName.trim().isEmpty() || dueDate.trim().isEmpty()) {
            Toast.makeText(this, "Please insert task name and due date", Toast.LENGTH_SHORT).show()
            return
        }

        val data: Intent = Intent()
        data.putExtra(EXTRA_TASK_NAME, taskName)
        data.putExtra(EXTRA_DESCRIPTION, description)
        data.putExtra(EXTRA_TAGS, tags)
        data.putExtra(EXTRA_STATUS, status)
        data.putExtra(EXTRA_DUE_DATE, dueDate)

        setResult(RESULT_OK, data)
        finish()
        scheduleNotification(getNotification(txtDate), cal.time.time)
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