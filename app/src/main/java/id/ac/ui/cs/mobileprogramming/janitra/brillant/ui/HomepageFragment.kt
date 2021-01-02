package id.ac.ui.cs.mobileprogramming.janitra.brillant.ui

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.opengl.GLES10
import android.opengl.GLSurfaceView
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import id.ac.ui.cs.mobileprogramming.janitra.brillant.MainActivity
import id.ac.ui.cs.mobileprogramming.janitra.brillant.util.InjectorUtils
import id.ac.ui.cs.mobileprogramming.janitra.brillant.vm.TaskViewModel
import id.ac.ui.cs.mobileprogramming.janitra.brillant.R
import id.ac.ui.cs.mobileprogramming.janitra.brillant.broadcastreceiver.NotificationRecorder
import kotlinx.android.synthetic.main.fragment_homepage.view.*
import java.io.IOException
import javax.microedition.khronos.opengles.GL10

class HomepageFragment: Fragment(), View.OnClickListener {
    private lateinit var mView: View
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var chronometer: Chronometer
    private lateinit var playButton: Button
    private lateinit var sosButton: Button
    private lateinit var notifBase: NotificationRecorder
    private var counter: Int = 0
    private lateinit var count: TextView
    private external fun addCounter(counter: Int): Int

    var mAudioRecorder: MediaRecorder? = null
    var running: Boolean = false
    var pauseOffset: Long = 0

    val PERMISSION_REQUEST_RECORD_AUDIO = 111

    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_homepage, container, false)

        mView.glSurface.setRenderer(GLRenderer())

        setTaskViewModel()

        val action = activity?.intent?.extras?.getString("action")

        if (action == NotificationRecorder.ACTION_STOP_RECORDER) {
            Toast.makeText(context, "Recording is stopped", Toast.LENGTH_LONG).show()
        }

        playButton = mView.findViewById(R.id.play_button)
        sosButton = mView.findViewById(R.id.start_stop_button)

        notifBase = NotificationRecorder(mView.context)

        chronometer = mView.findViewById(R.id.chronometer)

        sosButton.setOnClickListener(this)
        playButton.setOnClickListener(this)

        checkPermission()

        return mView
    }

    fun setTaskViewModel() {
        val taskFactory = InjectorUtils.provideTaskViewModelFactory(mView.context)

        taskViewModel = ViewModelProviders.of(this, taskFactory)
            .get(TaskViewModel::class.java)
        taskViewModel.getUnique().observe(this, Observer { listUnique ->
            showCurrentTaskNumber(listUnique.size)
        })
    }

    fun showCurrentTaskNumber(size: Int) {
        val taskNumber: TextView = mView.findViewById(R.id.task_number)

        taskNumber.setText("$size tasks")
    }

    class GLRenderer : GLSurfaceView.Renderer {
        var color = 0f
        var colorVelocity = 1f/60f

        override fun onDrawFrame(gl: GL10){
            if (color > 1 || color < 0){
                colorVelocity = -colorVelocity
            }
            color += colorVelocity

            gl.glClearColor(color * 0.5f, color, color, 1f)
            gl.glClear(GLES10.GL_COLOR_BUFFER_BIT)
        }

        override fun onSurfaceCreated(p0: GL10?, p1: javax.microedition.khronos.egl.EGLConfig?) {}
        override fun onSurfaceChanged(gl: GL10, width: Int, height: Int){}
    }

    fun checkPermission() {
        if (ContextCompat.checkSelfPermission(mView.context, "android.permission.RECORD_AUDIO") != PackageManager.PERMISSION_GRANTED) {

            if (shouldShowRequestPermissionRationale(Manifest.permission.RECORD_AUDIO)) {
                val builder = AlertDialog.Builder(context)
                builder.setMessage("Requiring a permission to access the microphone to record audio.")
                    .setTitle("Permission required")

                builder.setPositiveButton("OK") { dialog, id ->
                    requestPermission()
                }

                val dialog = builder.create()
                dialog.show()
            } else {
                requestPermission()
            }
        }
    }

    fun mediaRecorderSetup() {
        mAudioRecorder = MediaRecorder()

        val outputFile = context?.filesDir?.absolutePath + "/recording.3gp"

        mAudioRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        mAudioRecorder?.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        mAudioRecorder?.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB)
        mAudioRecorder?.setOutputFile(outputFile)
    }

    fun requestPermission() {
        requestPermissions(
            arrayOf(Manifest.permission.RECORD_AUDIO),
            PERMISSION_REQUEST_RECORD_AUDIO)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.start_stop_button -> {
                if (!running) {
                    startRecording(v)
                } else {
                    stopRecording(v)
                }
            }
            R.id.play_button -> {
                playRecord()
            }
        }
    }

    fun playRecord() {

        val outputFile = context?.filesDir?.absolutePath + "/recording.3gp"
        val mediaPlayer = MediaPlayer()
        try {
            mediaPlayer.setDataSource(outputFile)
            mediaPlayer.prepare()
            mediaPlayer.start()
            Toast.makeText(context, "Audio is playing", Toast.LENGTH_LONG)
                .show()
        } catch (e: Exception) {

        }
    }

    fun stopRecording(v: View) {

        notifBase.cancelAudioNotification()

        // Chronometer
        chronometer.stop()
        pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase()
        running = false
        sosButton.setText(R.string.start)
        pauseOffset = 0

        // Recorder
        mAudioRecorder?.stop()
        mAudioRecorder?.release()
        mAudioRecorder = null

        playButton.isEnabled = true
        Toast.makeText(context, "Recorder stopped", Toast.LENGTH_LONG).show()

        // Enable Navbar
        (activity as MainActivity).navigationToggle(true)
    }

    fun startRecording(v: View) {
        count = mView.findViewById(R.id.counter)

        notifBase.initAudioNotification()
        mediaRecorderSetup()

        // Chronometer
        chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset)
        chronometer.start()
        running = true
        sosButton.setText(R.string.stop)

        // Recorder
        try {
            mAudioRecorder?.prepare()
            mAudioRecorder?.start()
        } catch (ise: IllegalStateException) {
            Toast.makeText(context, "Error: (ISE)", Toast.LENGTH_LONG).show()

        } catch (ioe: IOException) {
            Toast.makeText(context, "Error: (ioe)", Toast.LENGTH_LONG).show()
        }
        playButton.isEnabled = false
        Toast.makeText(context, "Recording started", Toast.LENGTH_LONG).show()

        // Disable Navbar
        (activity as MainActivity).navigationToggle(false)

        counter = addCounter(counter)
        count.text = counter.toString()
    }
}